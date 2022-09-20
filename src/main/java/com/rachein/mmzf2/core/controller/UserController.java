package com.rachein.mmzf2.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/20
 * @Description
 */
@Api(tags = "用户模块")
@RequestMapping("user")
public class UserController {

    @ApiOperation("获取关注的用户列表")
    @GetMapping("/")
    public void list() {

    }

    @ApiOperation("获取用户info")
    @GetMapping("/info/{openId}")
    public void read(@PathVariable String openId) {

    }

    @ApiOperation("填充信息")
    @PostMapping("/info/fill/{openId}")
    public void fillInfo(@PathVariable String openId) {

    }

//    @ApiOperation("")


}
