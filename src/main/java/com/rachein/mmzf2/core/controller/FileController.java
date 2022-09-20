package com.rachein.mmzf2.core.controller;

import com.rachein.mmzf2.core.service.IFileService;
import com.rachein.mmzf2.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2022-09-19
 */
@Api(tags = "图床")
@RestController
@RequestMapping("api/file")
public class FileController {

    @Autowired
    private IFileService fileService;

    @ApiOperation("上传单文件")
    @PostMapping("/upload/1")
    public Result<String> upload(MultipartFile file, String type, String appid) {
        String path = fileService.upload(file, type, appid);
        return Result.success("上传成功！", path);
    }

}
