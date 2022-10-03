package com.rachein.mmzf2.core.service.impl;

import com.rachein.mmzf2.entity.DB.Article;
import com.rachein.mmzf2.core.mapper.ArticleMapper;
import com.rachein.mmzf2.core.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.mmzf2.entity.RO.ArticleAddRo;
import com.rachein.mmzf2.entity.VO.ArticleInfoVo;
import com.rachein.mmzf2.entity.VO.ArticleResultVo;
import com.rachein.mmzf2.entity.VO.ArticleVo;
import com.rachein.mmzf2.entity.VO.FileVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author 吴远健
 * @since 2022-09-30
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Override
    public FileVo coverUpload(MultipartFile file) {
        return null;
    }

    @Override
    public FileVo materialUpload(MultipartFile file) {
        return null;
    }

    @Override
    public ArticleResultVo saveToDraft(ArticleAddRo addRo) {
        return null;
    }

    @Override
    public void send(String media_id, String tag) {

    }

    @Override
    public void send(String media_id) {

    }

    @Override
    public void removeByArticleId(String localId) {

    }

    @Override
    public void updateByLocalId(String localId, ArticleAddRo updateRo) {

    }

    @Override
    public void removeArticleById(String localId) {

    }

    @Override
    public ArticleInfoVo view(String localId) {
        return null;
    }

    @Override
    public Long createDraft() {
        return null;
    }

    @Override
    public Long createArticle(Long draftId) {
        return null;
    }

    @Override
    public List<ArticleVo> listArticleByDraftId(Long draftId) {
        return null;
    }

    @Override
    public void removeDraftByDraftId(Long draftId) {

    }
}
