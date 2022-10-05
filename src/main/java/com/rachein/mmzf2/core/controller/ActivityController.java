package com.rachein.mmzf2.core.controller;

import com.rachein.mmzf2.core.service.IActivityService;
import com.rachein.mmzf2.entity.RO.ActivityAddRo;
import com.rachein.mmzf2.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private IActivityService activityService;

    @ApiOperation("添加活动")
    @PostMapping("/add")
    public Result<Long> add(@RequestBody ActivityAddRo ro) {
        Long id = activityService.save(ro);
        return Result.success(id);
    }





}
