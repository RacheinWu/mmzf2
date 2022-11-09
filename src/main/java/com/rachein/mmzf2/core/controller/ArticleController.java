package com.rachein.mmzf2.core.controller;

import com.rachein.mmzf2.core.service.IArticleService;
import com.rachein.mmzf2.core.service.IDraftService;
import com.rachein.mmzf2.entity.RO.ArticleAddRo;
import com.rachein.mmzf2.entity.VO.ArticleInfoVo;
import com.rachein.mmzf2.entity.VO.FileVo;
import com.rachein.mmzf2.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/20
 * @Description
 */
@Api(tags = "图文模块")
@RestController
class ArticleController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private IDraftService draftService;

    @ApiOperation(value = "上传推文封面", notes = "最近更新：永久图片素材新增后，将带有 URL 返回给开发者，开发者可以在腾讯系域名内使用（腾讯系域名外使用，图片将被屏蔽）。\n" +
            "缩略图（thumb）：64KB，支持 JPG 格式")
    @PostMapping("article/cover/upload")
    public Result<FileVo> uploadCover(MultipartFile file) {
        FileVo vo = articleService.coverUpload(file);
        return Result.success(vo);
    }

    @ApiOperation(value = "上传图文消息内的图片获取URL【订阅号与服务号认证后均可用】",
            tags = "请注意，本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下。")
    @PostMapping("article/material/upload")
    public Result<FileVo> uploadContentMaterial(MultipartFile file) {
        FileVo vo = articleService.materialUpload(file);
        return Result.success(vo);
    }


    @ApiOperation("创建文章")
    @PostMapping("article/create")
    public Result<Long> create(@RequestBody ArticleAddRo ro) {
        Long articleId = articleService.createArticle(ro);
        return Result.success(articleId);
    }

    /**
     * 待开发
     * @param draftId
     * @return
     */
//    @ApiOperation("撤回公布的推文")
//    @GetMapping("article/remove/{local_id}")
//    public Result<Object> removeArticleRelease(@PathVariable("local_id") String localId) {
//        return Result.success("撤销成功！");
//    }

    @ApiOperation("删除草稿中的文章【根据文章的id】")
    @GetMapping("article/remove/{article_id}")
    public Result<Object> removeArticle(@PathVariable("article_id") Long articleId) {
        articleService.removeArticleById(articleId);
        return Result.success("删除成功！");
    }

    @ApiOperation("更新草稿箱中的文章")
    @PostMapping("article/update")
    public Result<String> update(@RequestBody ArticleAddRo updateRo) {
        articleService.updateContentById(updateRo);
        return Result.success("更新草稿成功！");
    }

    @ApiOperation("获取文章详情")
    @GetMapping("article/{article_id}")
    public Result<ArticleInfoVo> getArticleInfo(@PathVariable("article_id") String articleId) {
        ArticleInfoVo articleInfoVo = articleService.getArticleInfoById(articleId);
        return Result.success(articleInfoVo);
    }

    @ApiOperation("更改文章标题")
    @PostMapping("article/title/update")
    public Result<Object> updateTile(@RequestParam("new_title") String newTile, @RequestParam("article_id") Long articleId) {
        articleService.updateTitle(articleId, newTile);
        return Result.success("修改成功");
    }


}
