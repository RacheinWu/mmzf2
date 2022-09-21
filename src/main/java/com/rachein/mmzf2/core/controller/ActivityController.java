package com.rachein.mmzf2.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/20
 * @Description
 */
@Api(tags = "活动模块")
@RestController
@RequestMapping("activity")
public class ActivityController {

    @ApiOperation("添加活动")
    public void add() {

    }
}
