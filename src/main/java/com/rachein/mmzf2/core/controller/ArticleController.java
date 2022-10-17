package com.rachein.mmzf2.core.controller;

import com.rachein.mmzf2.core.service.IArticleService;
import com.rachein.mmzf2.core.service.IDraftService;
import com.rachein.mmzf2.entity.DB.Draft;
import com.rachein.mmzf2.entity.RO.ArticleAddRo;
import com.rachein.mmzf2.entity.VO.ArticleInfoVo;
import com.rachein.mmzf2.entity.VO.ArticleVo;
import com.rachein.mmzf2.entity.VO.FileVo;
import com.rachein.mmzf2.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @ApiOperation("创建推文")
    @GetMapping("/draft/create")
    private Result<Long> draftCreate() {
        Draft draft = new Draft();
        draftService.save(draft);
        return Result.success(draft.getId());
    }


    @ApiOperation(value = "上传图文消息内的图片获取URL【订阅号与服务号认证后均可用】",
            tags = "请注意，本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下。")
    @PostMapping("article/material/upload")
    public Result<FileVo> uploadContentMaterial(MultipartFile file) {
        FileVo vo = articleService.materialUpload(file);
        return Result.success(vo);
    }


//    @ApiOperation(value = "群发推文", tags = "注意！本操作只面向已关注的人群，如果后面关注本公众号的，将无法接收到, 并且无法撤销！")
//    @GetMapping("article/release/{media_id}/{tag}")
//    public Result<Object> send(@PathVariable("media_id") String mediaId, @PathVariable("tag") String tag) {
//        articleService.send(mediaId, tag);
//        return Result.success(null);
//    }

    @ApiOperation("创建文章")
    @PostMapping("article/create")
    public Result<Long> create(@RequestBody ArticleAddRo ro) {
        Long articleId = articleService.createArticle(ro);
        return Result.success(articleId);
    }


//    @ApiOperation(value = "发布推文", tags = "本操作，将使得推文公开，所有人群包括没有订阅的人群也将可以访问")
//    @GetMapping("article/release/{media_id}")
//    public Result<Object> send(@PathVariable("media_id") String mediaId) {
//        articleService.send(mediaId);
//        return Result.success("发布成功！");
//    }


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

    @ApiOperation("删除整个推文【根据推文id】")
    @GetMapping("draft/remove/{draft_id}")
    public Result<Object> removeDraft(@PathVariable("draft_id") Long draftId) {
        articleService.removeDraftByDraftId(draftId);
        return Result.success("删除成功");
    }

    @ApiOperation("删除草稿中的文章【根据文章的id】")
    @GetMapping("article/remove/{article_id}")
    public Result<Object> removeArticle(@PathVariable("article_id") Long articleId) {
        articleService.removeArticleById(articleId);
        return Result.success("删除成功！");
    }

    @ApiOperation("更新草稿箱中的文章")
    @PostMapping("draft/article/update/{article_id}")
    public Result<String> update(@PathVariable("article_id") String articleId, @RequestBody ArticleAddRo updateRo) {
        articleService.updateByIdRedis(articleId, updateRo);
        return Result.success("更新草稿成功！");
    }

    @ApiOperation("获取文章详情")
    @GetMapping("article/{article_id}")
    public Result<ArticleInfoVo> getArticleInfo(@PathVariable("article_id") String articleId) {
        ArticleInfoVo articleInfoVo = articleService.getArticleInfoById(articleId);
        return Result.success(articleInfoVo);
    }

    @ApiOperation("查看某一草稿的文章粗略")
    @GetMapping("draft/{draft_id}")
    public Result<List<ArticleVo>> listArticleByDraftId(@PathVariable("draft_id") Long draftId) {
        List<ArticleVo> articleVos = articleService.listArticleByDraftId(draftId);
        return Result.success(articleVos);
    }

}
