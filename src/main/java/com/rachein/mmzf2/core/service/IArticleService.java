package com.rachein.mmzf2.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rachein.mmzf2.entity.DB.Article;
import com.rachein.mmzf2.entity.RO.ArticleAddRo;
import com.rachein.mmzf2.entity.VO.ArticleInfoVo;
import com.rachein.mmzf2.entity.VO.ArticleResultVo;
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

    ArticleResultVo saveToDraft(ArticleAddRo addRo);

    void send(String media_id, String tag);

    void send(String media_id);

    void removeByArticleId(String localId);

    void updateByLocalId(String localId, ArticleAddRo updateRo);

    void removeArticleById(String localId);

    ArticleInfoVo view(String localId);

    Long createDraft();

    Long createArticle(Long draftId, Long activityId);

    List<ArticleVo> listArticleByDraftId(Long draftId);

    void removeDraftByDraftId(Long draftId);

}
