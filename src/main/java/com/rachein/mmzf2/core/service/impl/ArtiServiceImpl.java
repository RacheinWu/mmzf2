package com.rachein.mmzf2.core.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.mmzf2.core.mapper.FileMapper;
import com.rachein.mmzf2.core.service.*;
import com.rachein.mmzf2.entity.DB.*;
import com.rachein.mmzf2.entity.RO.ArticleAddRo;
import com.rachein.mmzf2.entity.VO.ArticleInfoVo;
import com.rachein.mmzf2.entity.VO.ArticleVo;
import com.rachein.mmzf2.entity.VO.FileVo;
import com.rachein.mmzf2.exception.GlobalException;
import com.rachein.mmzf2.redis.RedisService;
import com.rachein.mmzf2.redis.myPrefixKey.ArticleKey;
import com.rachein.mmzf2.result.CodeMsg;
import com.rachein.mmzf2.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 物联网工程系 ITAEM 唐奕泽 | 计算机科学系 ITAEM 吴远健
 * @Description
 *
 *
 * @date 2022/9/20 21:40
 */
@Slf4j
@Transactional
@Service
public class ArtiServiceImpl extends ServiceImpl<BaseMapper<Article>, Article> implements IArticleService {

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private IDraftService draftService;

    @Autowired
    private IDraftArticleService draftArticleService;

    @Autowired
    private IFileService fileService;

    @Autowired
    private IActivityService activityService;

    @Autowired
    RedisService redisService;

    @Value("resource.article.defaultCoverUrl")
    private String default_coverUrl;


    @Override
    public FileVo coverUpload(MultipartFile file) {
        FileUtils.judge(file, 5000l, "img");
        FileDB save = FileUtils.save(file, null, null);
        fileService.save(save);
        FileVo vo = new FileVo();
        BeanUtils.copyProperties(save, vo);
        return vo;
    }

    @Override
    public FileVo materialUpload(MultipartFile file) {
        FileUtils.judge(file, 5000l, "img");
        FileDB save = FileUtils.save(file, null, null);
        fileService.save(save);
        FileVo vo = new FileVo();
        BeanUtils.copyProperties(save, vo);
        return vo;
    }

    @Override
    public Long createArticle(ArticleAddRo ro) {
        //先从数据库中，找到对应的活动
        Activity activity = activityService.lambdaQuery()
                .eq(Activity::getId, ro.getActivityId())
                .one();
        if (Objects.isNull(activity)) throw new GlobalException(CodeMsg.BIND_ERROR);
        //数据库创建article:
        Article article = new Article();
        article.setDraftId(ro.getDraftId());
        article.setActivityId(ro.getActivityId());
        save(article);
        //redis中也保存一份
//        redisService.set(ArticleKey.getById,articleId.toString(),article);
        return article.getId();
    }

    @Override
    public void updateByIdRedis(String articleId, ArticleAddRo updateRo) {
        Article article = new Article();
        BeanUtils.copyProperties(updateRo, article);
        //更新到redis中
        redisService.set(ArticleKey.getById, article.getId().toString(), article);
    }

    @Override
    public Map<Long, List<ArticleVo>> listDraft() {
        //获取数据库中所有的推文的id
        Map<Long, List<ArticleVo>> map = new HashMap<>();
        Set<Long> draftIds = draftService.lambdaQuery()
                .select(Draft::getId)
                .list()
                .stream().map(Draft::getId)
                .collect(Collectors.toSet());
        //从article表中
        for (Long draftId : draftIds) {
            List<ArticleVo> articleVos = listArticleByDraftId(draftId);
            if (Objects.isNull(articleVos)) {
                continue;
            }
            map.put(draftId, articleVos);
        }
        return map;
    }

    @Override
    public void removeArticleById(Long articleId) {
        //从redis中清除
        redisService.delete(ArticleKey.getById,articleId.toString());
        //从文章表中清除
        lambdaUpdate().eq(Article::getId, articleId).remove();
    }

    @Override
    public ArticleInfoVo getArticleInfoById(String articleId) {
        //先从redis中获取信息
        Article article = redisService.get(ArticleKey.getById, articleId, Article.class);
        ArticleInfoVo infoVo = new ArticleInfoVo();
        //如果redis中没数据，那么从数据库中获取信息:
        if (Objects.isNull(article)){
            article = lambdaQuery()
                    .eq(Article::getId, articleId)
                    .select(Article::getId, Article::getContent, Article::getAuthor, Article::getTitle)
                    .one();
        }
        BeanUtils.copyProperties(article, infoVo);
        return infoVo;
    }


    @Override
    /**
     * //数据库创建Draft
     * //return id
     */
    public Long createDraft() {
        Draft draft = new Draft();
        draftService.save(draft);
//        //同时创建自动创建一个文章：
//        Article a = new Article();
//        a.setDraftId(draft.getId());
//        save(a);
        return draft.getId();
    }

    @Override
    public List<ArticleVo> listArticleByDraftId(Long draftId) {
        //从数据库中获取中间表的信息：
//        List<DraftArticleRelation> relations = draftArticleService.lambdaQuery()
//                .eq(DraftArticleRelation::getDraftId, draftId)
//                .list();
//        //遍历中间表信息，获取每一个article的信息:
//        List<ArticleVo> articleVos = new ArrayList<>();
//        relations.forEach(t -> {
//            Article article = lambdaQuery()
//                    .eq(Article::getId, t.getArticleId())
//                    .select(Article::getTitle, Article::getCoverPath, Article::getId)
//                    .one();
//            ArticleVo articleVo = new ArticleVo();
//            BeanUtils.copyProperties(article, articleVo);
//            articleVos.add(articleVo);
//        });

        /**
         * 1.从article表 找到draft_id = draftId，
         *  select(Article::getTitle, Article::getCoverPath, Article::getId)
         * 直接返回
         */
        List<Article> darList = lambdaQuery().eq(Article::getDraftId, draftId).list();
        List<ArticleVo> articleVos = new ArrayList<>();
        darList.forEach(d -> {
            ArticleVo articleVo = new ArticleVo();
            BeanUtils.copyProperties(d,articleVo);
            articleVos.add(articleVo);
        });
        return articleVos;
    }

    @Override
    public void removeDraftByDraftId(Long draftId) {
//        从draft表中移除
//        draftService.lambdaUpdate().eq(Draft::getId, draftId).remove();
//        //从中间表中
//        //搜索关联的article的id
//        List<Long> articleIds = draftArticleService.lambdaQuery()
//                .eq(DraftArticleRelation::getDraftId, draftId)
//                .select(DraftArticleRelation::getArticleId)
//                .list()
//                .stream()
//                .map(t -> {
//                    return t.getArticleId();
//                })
//                .collect(Collectors.toList());
//        draftArticleService.lambdaUpdate().eq(DraftArticleRelation::getDraftId, draftId).remove();
//        //从article表中移除
//        lambdaUpdate().eq(Article::getId, articleIds).remove();

        /**
         * 1、先从article表中删除 draft_id = draftId（先保存id -》 List集合）
         * 2 从draft 删除id
         * 3 redis 删除对应 （根据list集合删除）
         */

        List<Article> articleList = lambdaQuery().eq(Article::getDraftId, draftId).list();
        draftService.lambdaUpdate().eq(Draft::getId,draftId).remove();
        articleList.forEach(l ->{
            redisService.delete(ArticleKey.getById,l.getId().toString());
            lambdaUpdate().eq(Article::getId,l.getId()).remove();
        });
    }


    @Override
    public void send(String media_id, String tag) {

    }

    @Override
    public void send(String media_id) {

    }
}
