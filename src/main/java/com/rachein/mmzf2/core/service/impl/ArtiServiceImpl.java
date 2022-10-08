package com.rachein.mmzf2.core.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.mmzf2.core.mapper.FileMapper;
import com.rachein.mmzf2.core.service.*;
import com.rachein.mmzf2.entity.DB.*;
import com.rachein.mmzf2.entity.RO.ArticleAddRo;
import com.rachein.mmzf2.entity.VO.ArticleInfoVo;
import com.rachein.mmzf2.entity.VO.ArticleResultVo;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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


    @Override
    public FileVo coverUpload(MultipartFile file) {
        FileUtils.judge(file, 5000l, "img");
        FileDB save = FileUtils.save(file, null, null);
        fileService.save(save);
        FileVo vo = new FileVo();
        BeanUtils.copyProperties(save, vo);
//        vo.setId(save.getId());
//        vo.setLocalUrl(save.getLocalUrl());
        return vo;
//        String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="+ AccessTokenUtil.getToken() +"&type=thumb";
//        String media_id;
//        //校验数据
////        FileUtils.judge(file, 5000l, "img");
//        //上传到微信服务器
//        Response response = HttpRequestUtils.post(url, file, "media");
//        try {
//            String responseJson = response.body().string();
//            media_id = JSON.parseObject(responseJson).getString("media_id");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        //备份到本地服务器中
//        FileVo save = FileUtils.save(file, null, media_id);
//        return save;
    }

    @Override
    public FileVo materialUpload(MultipartFile file) {
        FileUtils.judge(file, 5000l, "img");
        FileDB save = FileUtils.save(file, null, null);
        fileService.save(save);
        FileVo vo = new FileVo();
        BeanUtils.copyProperties(save, vo);
        return vo;


//        String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + AccessTokenUtil.getToken();
//        String vx_url;
//        //先进行检验文件
////        FileUtils.judge(file, 5000l, "img");
//        //上传到微信服务器中
//        Response response = HttpRequestUtils.post(url, file, "media");
//        try {
//            String responseJson = response.body().string();
//            vx_url = JSON.parseObject(responseJson).getString("url");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        //保存到本地硬盘,以及数据库
//        FileVo save = FileUtils.save(file, vx_url, null);
//        //返回链接
//        return save;
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
        Long articleId = article.getId();
        //redis中也保存一份
        redisService.set(ArticleKey.getById,articleId.toString(),article);
        return articleId;
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
    public Long createDraft() {
        /**
         * //数据库创建Draft
         * //return id
         */
        return null;
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
        return null;
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
    }


    @Override
    public void send(String media_id, String tag) {

    }

    @Override
    public void send(String media_id) {

    }
}
