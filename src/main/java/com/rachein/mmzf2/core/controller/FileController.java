package com.rachein.mmzf2.core.controller;

import com.rachein.mmzf2.entity.VO.FileVo;
import com.rachein.mmzf2.result.Result;
import com.rachein.mmzf2.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件 前端控制器
 * </p>
 *
 * @author 吴远健
 * @since 2022-09-30
 */
@Api(tags = "文件模块")
@RestController
@RequestMapping("file")
public class FileController {

    @ApiOperation("上传【返回url】")
    @PostMapping("/upload")
    public Result<String> upload(@Param("file") MultipartFile file) {
        return Result.success(FileUtils.save(file));
    }

//    @ApiOperation(value = "上传推文封面", notes = "最近更新：永久图片素材新增后，将带有 URL 返回给开发者，开发者可以在腾讯系域名内使用（腾讯系域名外使用，图片将被屏蔽）。\n" +
//            "缩略图（thumb）：64KB，支持 JPG 格式")
//    @PostMapping("article/cover/upload")
//    public Result<FileVo> uploadCover(MultipartFile file) {
//        FileVo vo = articleService.coverUpload(file);
//        return Result.success(vo);
//    }
//
//
//    @ApiOperation(value = "上传图文消息内的图片获取URL【订阅号与服务号认证后均可用】",
//            tags = "请注意，本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下。")
//    @PostMapping("article/material/upload")
//    public Result<FileVo> uploadContentMaterial(MultipartFile file) {
//        FileVo vo = articleService.materialUpload(file);
//        return Result.success(vo);
//    }
}
