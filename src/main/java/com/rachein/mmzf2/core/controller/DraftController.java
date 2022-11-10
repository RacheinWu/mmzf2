package com.rachein.mmzf2.core.controller;

import com.rachein.mmzf2.core.service.IArticleService;
import com.rachein.mmzf2.core.service.IDraftService;
import com.rachein.mmzf2.entity.DB.Article;
import com.rachein.mmzf2.entity.DB.Draft;
import com.rachein.mmzf2.entity.RO.DraftApplicationRo;
import com.rachein.mmzf2.entity.RO.DraftCheckRo;
import com.rachein.mmzf2.entity.VO.ArticleVo;
import com.rachein.mmzf2.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/11/7
 * @Description
 */
@Api(tags = "推文模块")
@RestController
public class DraftController {


    @Autowired
    private IDraftService draftService;

    @Autowired
    private IArticleService articleService;

    @ApiOperation("创建推文")
    @GetMapping("/draft/create")
    private Result<Map<String, Object>> draftCreate() {
        Draft draft = new Draft();
        draftService.save(draft);
        Map<String, Object> map = new HashMap<>();
        map.put("draft_id", draft.getId());
        return Result.success(map);
    }

    @ApiOperation("获取推文申请列表")
    @GetMapping("/draft/review/all")
    public Result<Map<String, List>> listApplication() {
        Map<String, List> map = draftService.listApplication();
        return Result.success(map);
    }

    @ApiOperation("推文草稿箱获取")
    @GetMapping("/draft/all")
    public Result<List<Map<String, Object>>> list() {
        List<Map<String, Object>> ans = draftService.listDrawing();
        return Result.success(ans);
    }

    @ApiOperation("查看某一草稿的文章粗略")
    @GetMapping("draft/{draft_id}")
    public Result<List<ArticleVo>> listArticleByDraftId(@PathVariable("draft_id") Long draftId) {
        List<ArticleVo> articleVos = articleService.listArticleByDraftId(draftId);
        return Result.success(articleVos);
    }

    @ApiOperation("申请发布推文，参数是标签")
    @PostMapping("draft/publish/application")
    public Result<String> application(@RequestBody DraftApplicationRo ro) {
        draftService.application(ro);
        return Result.success("申请成功！请等待一级管理员审核。");
    }

    @ApiOperation("获取推文对应发布的标签")
    @GetMapping("draft/tag/{draft_id}")
    public Result<Map<String, Object>> listTag(@PathVariable("draft_id") Long draftId) {
        Map<String, Object> map = draftService.listTag(draftId);
        return Result.success(map);
    }

    @ApiOperation("管理员审核推文")
    @PostMapping("draft/public/check")
    public Result<Object> check(@RequestBody DraftCheckRo ro) {
        draftService.check(ro);
        return Result.success("success");
    }


    @ApiOperation("删除整个推文【根据推文id】")
    @GetMapping("draft/remove/{draft_id}")
    public Result<Object> removeDraft(@PathVariable("draft_id") Long draftId) {
        articleService.removeDraftByDraftId(draftId);
        return Result.success("删除成功");
    }


}
