package com.rachein.mmzf2.core.controller;

import com.rachein.mmzf2.core.service.ViewAutoService;
import com.rachein.mmzf2.entity.VAO.UserFill;
import com.rachein.mmzf2.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/23
 * @Description
 */
@Api(tags = "前端界面全自动编程controller")
@RestController
@RequestMapping("view")
public class ViewController {

    @Autowired
    private ViewAutoService viewAutoService;

    @ApiOperation("用户补充信息表单")
    @GetMapping("/userFill/{id}")
    public Result<List<UserFill>> listUserFill(@PathVariable Integer id) {
        List<UserFill> userFills = viewAutoService.listUserFill(id);
        return Result.success(userFills);
    }


    @ApiOperation("用户信息表单")
    @GetMapping("/table/user")
    public void tableUser() {
//        viewAutoService.
    }
}
