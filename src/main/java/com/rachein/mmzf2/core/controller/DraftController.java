package com.rachein.mmzf2.core.controller;

import com.rachein.mmzf2.core.service.IArticleService;
import com.rachein.mmzf2.core.service.IDraftService;
import com.rachein.mmzf2.core.service.ITableHeadVoService;
import com.rachein.mmzf2.entity.DB.Article;
import com.rachein.mmzf2.entity.DB.Draft;
import com.rachein.mmzf2.entity.DB.TableHeadVo;
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
    private ITableHeadVoService tableHeadVoService;

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
        Map<String, List> map = new HashMap<>();
        //表头
        List<TableHeadVo> head = tableHeadVoService.lambdaQuery()
                .eq(TableHeadVo::getBelong, "draft-review::list")
                .list();
        //数据
        List<Draft> body = draftService.lambdaQuery()
//                .eq()
                .list();
        //
        map.put("head", head);
        map.put("body", body);
        return Result.success(map);
    }

    @ApiOperation("推文草稿箱获取")
    @GetMapping("/draft/all")
    public Result<List<Map<String, Object>>> list() {
        //集装箱
        List<Map<String, Object>> ans = new ArrayList<>();
        //先获取状态为未发布的推文id
        List<Long> draftIds = draftService.lambdaQuery()
                .eq(Draft::getState, "未发布")
                .select(Draft::getId)
                .list()
                .stream().map(Draft::getId)
                .collect(Collectors.toList());
        //从文章表中获取对应头节点的子节点们
        draftIds.forEach(id -> {
            Map<String, Object> draft = new HashMap<>();
            List<Article> list = articleService.lambdaQuery()
                    .eq(Article::getDraftId, id)
                    .select(Article::getId, Article::getCoverPath, Article::getTitle)
                    .list();
            draft.put("draft_id", id);
            draft.put("articles", list);
            ans.add(draft);
        });
        return Result.success(ans);
    }

    @ApiOperation("删除整个推文【根据推文id】")
    @GetMapping("draft/remove/{draft_id}")
    public Result<Object> removeDraft(@PathVariable("draft_id") Long draftId) {
        articleService.removeDraftByDraftId(draftId);
        Article article = new Article();
        return Result.success("删除成功");
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


    @ApiOperation("管理员审核推文")
    @PostMapping("draft/public/check")
    public void check(@RequestBody DraftCheckRo ro) {
        draftService.check(ro);
    }
    //    @ApiOperation(value = "发布推文", tags = "本操作，将使得推文公开，所有人群包括没有订阅的人群也将可以访问")
//    @GetMapping("draft/release/{media_id}")
//    public Result<Object> send(@PathVariable("media_id") String mediaId) {
//        articleService.send(mediaId);
//        return Result.success("发布成功！");
//    }

    //    @ApiOperation(value = "群发推文", tags = "注意！本操作只面向已关注的人群，如果后面关注本公众号的，将无法接收到, 并且无法撤销！")
//    @GetMapping("article/release/{media_id}/{tag}")
//    public Result<Object> send(@PathVariable("media_id") String mediaId, @PathVariable("tag") String tag) {
//        articleService.send(mediaId, tag);
//        return Result.success(null);
//    }


}
