package com.rachein.mmzf2.core.service.impl;

import com.rachein.mmzf2.core.service.IUserRoleService;
import com.rachein.mmzf2.core.service.IUserService;
import com.rachein.mmzf2.entity.DB.Role;
import com.rachein.mmzf2.core.mapper.RoleMapper;
import com.rachein.mmzf2.core.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.mmzf2.entity.DB.User;
import com.rachein.mmzf2.entity.DB.UserRole;
import com.rachein.mmzf2.entity.RO.AdminApplyRo;
import com.rachein.mmzf2.entity.VO.AdminApplyVo;
import com.rachein.mmzf2.entity.VO.UserVo;
import com.rachein.mmzf2.exception.GlobalException;
import com.rachein.mmzf2.result.CodeMsg;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 吴远健
 * @since 2022-09-30
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IUserService userService;

    @Override
    public List<UserVo> listAdmin(Integer level) {
        //从中间表中获取对象
        List<UserRole> userRoles = userRoleService.lambdaQuery()
                .eq(UserRole::getRoleId, level)
                .list();
        //从user表中索引:
        List<String> ids = userRoles.stream().map(UserRole::getOpenid).collect(Collectors.toList());
        return userService.listUserByIds(ids);

//
////        List<UserVo> userVos = new ArrayList<>();
////        userRoles.forEach(userRole -> {
////            User db = userService.lambdaQuery()
////                    .eq(User::getOpenid, userRole.getOpenid())
//////                    .select(User::getOpenid, User::getNickname, User::getOpenid)
////                    .one();
////            UserVo userVo= new UserVo();
////            userVo.setRemark(userRole.getRemark());
////            BeanUtils.copyProperties(db, userVo);
////            userVos.add(userVo);
////        });
//        return userVos;
    }

    @Override
    public void checkApplyIIByMid(Long userRoleId, Boolean result) {

    }

    @Override
    public List<UserRole> listAdminApply() {
        //先从中间表中查询 -> map(openId, remarks)
        List<UserRole> list = userRoleService.lambdaQuery()
                .eq(UserRole::getStatus, 0)
                .list();
        List<UserVo> userVos = userService.listUserByIds(list.stream().map(UserRole::getOpenid).collect(Collectors.toList()));
        userVos.forEach(vo -> {
            AdminApplyVo adminApplyVo = new AdminApplyVo();
            BeanUtils.copyProperties(vo, adminApplyVo);
//            adminApplyVo
        });
        return null;
    }

    @Override
    public void grantII(AdminApplyRo body) {
        //检查数据库是否存在此人：
        boolean exists = userService.lambdaQuery().eq(User::getOpenid, body.getOpenId()).exists();
        if (!exists) throw new GlobalException(CodeMsg.USER_NOT_FOUND);
        //将审核状态改为 未审核-> 0
        UserRole userRole = new UserRole();
        userRole.setStatus(0);
        //从中间表中，添加数据记录
        userRole.setRoleId(body.getLevel());
        userRole.setRemark(body.getRemark());
        userRoleService.save(userRole);
    }
}
