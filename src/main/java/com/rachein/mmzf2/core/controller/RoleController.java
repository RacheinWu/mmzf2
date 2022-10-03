package com.rachein.mmzf2.core.controller;

import com.rachein.mmzf2.core.service.IRoleService;
import com.rachein.mmzf2.entity.RO.AdminApplyRo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 吴远健
 * @since 2022-09-30
 */
@Api(tags = "角色列表")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation("查看i级管理员列表")
    @GetMapping("/admin/{level}")
    public void listAdminII(@PathVariable Integer level) {
        roleService.listAdmin(level);
    }

    @ApiOperation("赋予二级管理员")
    @PostMapping("/admin/authorized/2")
    public void grantAdminII(@RequestBody AdminApplyRo body) {
        roleService.grantII(body);
    }

    @ApiOperation("审核通过 二级管理员通过")
    @GetMapping("/admin/check/{user_role_id}/{result}")
    public void check(@PathVariable("user_role_id") Long userRoleId, @PathVariable Boolean result) {
        roleService.checkApplyIIByMid(userRoleId, result);
    }

    @ApiOperation("查看所有管理员身份申请")
    @GetMapping("/admin/check")
    public void listCheck() {
        roleService.listAdminApply();
    }

}
