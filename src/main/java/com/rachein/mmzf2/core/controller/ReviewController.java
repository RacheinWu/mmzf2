package com.rachein.mmzf2.core.controller;

import com.rachein.mmzf2.core.service.IArticleService;
import com.rachein.mmzf2.entity.RO.ArticleReviewRo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/26
 * @Description
 */
@Api(tags = "审查模块")
@RestController
@RequestMapping("review")
public class ReviewController {

    @Autowired
    private IArticleService articleService;

    @ApiOperation("推文审核")
    @GetMapping("/article")
    public void article(@RequestBody ArticleReviewRo ro) {
        articleService.review(ro);
    }
}
