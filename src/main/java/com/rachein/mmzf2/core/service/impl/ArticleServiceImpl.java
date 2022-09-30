package com.rachein.mmzf2.core.service.impl;

import com.rachein.mmzf2.entity.new.DB.Article;
import com.rachein.mmzf2.core.mapper.ArticleMapper;
import com.rachein.mmzf2.core.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
