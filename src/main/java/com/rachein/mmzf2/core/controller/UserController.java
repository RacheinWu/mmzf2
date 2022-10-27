package com.rachein.mmzf2.core.controller;

import com.rachein.mmzf2.core.service.IUserService;
import com.rachein.mmzf2.entity.DB.StudentHighInfo;
import com.rachein.mmzf2.entity.DB.StudentLow12Info;
import com.rachein.mmzf2.entity.DB.StudentLow3Info;
import com.rachein.mmzf2.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/20
 * @Description
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("user")
public class UserController {

    private IUserService userService;

//    @ApiOperation("获取关注的用户列表")
//    @GetMapping("/all")
//    public Result<List<User>> list() {
////        List<User> list = userService.lambdaQuery().eq(User::getIsAdmin, false).list();
////        return Result.success(list);
//        return null;
//    }

    @ApiOperation("获取用户info")
    @GetMapping("/info/{openId}")
    public Result<Object> read(@PathVariable String openId) {
        Object info = userService.getByOpenId(openId);
        return Result.success(info);
    }

//    /**
//     *
//     * @param openId 微信号
//     * @param typeIndex 是什么人群，大学生还是社会人士，还是高三还是高一二
//     * @param info 请求体
//     */
//    @ApiOperation("填充信息")
//    @PostMapping("/info/fill/{openId}/{type_index}")
//    public void fillInfo(@PathVariable String openId, @PathVariable("type_index") Integer typeIndex, @RequestBody UserUpdateRo info) {
//        //switch语句进行选择 service 从而保存信息
//
//        userService.updateInfo(openId, info, );
//    }

    @ApiOperation("填充信息【大学生】")
    @PostMapping("/info/fill/high_student")
    public void fillInfo(@RequestBody StudentHighInfo info) {
        userService.updateInfoToHighStudent(info);
    }

    @ApiOperation("填充信息【高一二学生】")
    @PostMapping("/info/fill/low_student_12")
    public void fillInfo(@RequestBody StudentLow12Info info) {
        userService.updateInfoToLowStudent12(info);
    }

    @ApiOperation("填充信息【高三学生】")
    @PostMapping("/info/fill/low_student_3")
    public void fillInfo(@RequestBody StudentLow3Info info) {
        userService.updateInfoToLowStudent3(info);
    }


    @ApiOperation("会员列表")
    @GetMapping("/all")
    public Result<Map<String, List>> listUser() {
        Map<String, List> res = userService.listUser();
        return Result.success(res);
    }

}
