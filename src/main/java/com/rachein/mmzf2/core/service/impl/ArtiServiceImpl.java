package com.rachein.mmzf2.core.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.mmzf2.core.mapper.FileMapper;
import com.rachein.mmzf2.core.service.*;
import com.rachein.mmzf2.entity.DB.*;
import com.rachein.mmzf2.entity.RO.ArticleAddRo;
import com.rachein.mmzf2.entity.RO.ArticleReviewRo;
import com.rachein.mmzf2.entity.VO.ArticleInfoVo;
import com.rachein.mmzf2.entity.VO.ArticleVo;
import com.rachein.mmzf2.entity.VO.FileVo;
import com.rachein.mmzf2.exception.GlobalException;
import com.rachein.mmzf2.redis.RedisService;
import com.rachein.mmzf2.redis.myPrefixKey.ArticleKey;
import com.rachein.mmzf2.result.CodeMsg;
import com.rachein.mmzf2.utils.FileUtils;
import com.rachein.mmzf2.utils.MultipartToFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    private IArticleService articleService;

    @Autowired
    private VXServiceImpl vxService;

    @Autowired
    RedisService redisService;

    @Value("resource.article.defaultCoverUrl")
    private String default_coverUrl;


    @Override
    public FileVo coverUpload(MultipartFile multipartFile, Long articleId) {
        // 校验文件
        FileUtils.judge(multipartFile, 6400L, "img");
        // 保存到本地
        Map<String, Object> map = FileUtils.save2(multipartFile);
        File file = (File) map.get("file");
        String relative_path = (String) map.get("relative_path");
        // 上传到微信服务器上
        String media_id = vxService.uploadCover(file);
        // 文章表更新
        String network_path = FileUtils.local_url + FileUtils.reflect_path_prefix + relative_path;
        articleService.lambdaUpdate()
                .eq(Article::getDraftId, articleId)
                .set(Article::getThumb_media_id, media_id)
                .set(Article::getCoverPath, network_path)
                .update();
        FileVo vo = new FileVo();
        vo.setUrl(network_path);
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
        return article.getId();
    }

    @Override
    public void updateContentById(ArticleAddRo ro) {
        //复制
        Article article = new Article();
        BeanUtils.copyProperties(ro, article);
        //更新到redis中
        redisService.set(ArticleKey.getById, article.getId().toString(), article);
    }

    @Override
    public Map<Long, List<ArticleVo>> listDraft() {
        //获取数据库中所有的推文的id（未发布的）
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
    public void updateTitle(Long articleId, String newTile) {
        lambdaUpdate().eq(Article::getId, articleId).set(Article::getTitle, newTile).update();

    }

    @Override
    public void updateAuthor(Long articleId, String author) {
        lambdaUpdate().eq(Article::getId, articleId).set(Article::getAuthor, author).update();
    }

    @Override
    public void removeArticleById(Long articleId) {
//        //从redis中清除
//        redisService.delete(ArticleKey.getById,articleId.toString());
        //从文章表中清除
        lambdaUpdate().eq(Article::getId, articleId).remove();
        //清楚
    }

    @Override
    public ArticleInfoVo getArticleInfoById(String articleId) {
        Article article = lambdaQuery().eq(Article::getId, articleId).one();
        ArticleInfoVo infoVo = new ArticleInfoVo();
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
