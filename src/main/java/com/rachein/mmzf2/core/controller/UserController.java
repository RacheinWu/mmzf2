package com.rachein.mmzf2.core.controller;

import com.rachein.mmzf2.core.service.IUserService;
import com.rachein.mmzf2.entity.DB.User;
import com.rachein.mmzf2.entity.RO.UserUpdateRo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/20
 * @Description
 */
@Api(tags = "用户模块")
@RequestMapping("user")
public class UserController {

    IUserService userService;

    @ApiOperation("获取关注的用户列表")
    @GetMapping("/")
    public void list() {
//        userService.lambdaQuery().select(User::getOpenid, User::getNickname, User::get)
    }

    @ApiOperation("获取用户info")
    @GetMapping("/info/{openId}")
    public void read(@PathVariable String openId) {

    }

    @ApiOperation("填充信息")
    @PostMapping("/info/fill/{openId}")
    public void fillInfo(@PathVariable String openId, @RequestBody UserUpdateRo info) {
        userService.updateInfo(openId, info);
    }

//    @ApiOperation("")


}
