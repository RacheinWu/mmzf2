package com.rachein.mmzf2.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rachein.mmzf2.entity.DB.Article;
import com.rachein.mmzf2.entity.RO.ArticleAddRo;
import com.rachein.mmzf2.entity.VO.ArticleInfoVo;
import com.rachein.mmzf2.entity.VO.ArticleVo;
import com.rachein.mmzf2.entity.VO.FileVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/20
 * @Description
 */
public interface IArticleService extends IService<Article> {

    FileVo coverUpload(MultipartFile file);

    FileVo materialUpload(MultipartFile file);

    void send(String media_id, String tag);

    void send(String media_id);

    void removeArticleById(Long localId);

    ArticleInfoVo getArticleInfoById(String localId);

    Long createDraft();

    List<ArticleVo> listArticleByDraftId(Long draftId);

    void removeDraftByDraftId(Long draftId);

    Long createArticle(ArticleAddRo ro);

    void updateByIdRedis(String articleId, ArticleAddRo updateRo);
}
