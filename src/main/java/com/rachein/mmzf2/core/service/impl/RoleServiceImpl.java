package com.rachein.mmzf2.core.service.impl;

import com.rachein.mmzf2.core.service.IUserRoleService;
import com.rachein.mmzf2.core.service.IUserService;
import com.rachein.mmzf2.entity.DB.Role;
import com.rachein.mmzf2.core.mapper.RoleMapper;
import com.rachein.mmzf2.core.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.mmzf2.entity.DB.User;
import com.rachein.mmzf2.entity.DB.AdminApply;
import com.rachein.mmzf2.entity.RO.AdminApplyRo;
import com.rachein.mmzf2.entity.VO.AdminApplyVo;
import com.rachein.mmzf2.entity.VO.UserVo;
import com.rachein.mmzf2.exception.GlobalException;
import com.rachein.mmzf2.result.CodeMsg;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
        List<AdminApply> adminApplies = userRoleService.lambdaQuery()
                .eq(AdminApply::getAdminLevel, level)
                .list();
        //从user表中索引:
        List<String> ids = adminApplies.stream().map(AdminApply::getOpenid).collect(Collectors.toList());
        return userService.listUserByIds(ids);
    }

    @Override
    public void checkApplyIIByMid(Long userRoleId, Integer result) {
        //从中间表中确认是否有此申请
        AdminApply one = userRoleService.lambdaQuery()
                .eq(AdminApply::getId, userRoleId)
                .one();
        if (Objects.isNull(one)) throw new GlobalException(CodeMsg.BIND_ERROR);
        //进行状态更新
        one.setStatus(result);
        userRoleService.updateById(one);
    }

    @Override
    public List<AdminApply> listAdminApply() {
        //先从中间表中查询 -> map(openId, remarks)
        List<AdminApply> list = userRoleService.lambdaQuery()
                .eq(AdminApply::getStatus, 0)
                .list();
        List<UserVo> userVos = userService.listUserByIds(list.stream().map(AdminApply::getOpenid).collect(Collectors.toList()));
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
        boolean exists = userService.lambdaQuery().eq(User::getOpenid, body.getOpenid()).exists();
        if (!exists) throw new GlobalException(CodeMsg.USER_NOT_FOUND);
        //将审核状态改为 未审核-> 0
        AdminApply adminApply = new AdminApply();
        adminApply.setStatus(0);
        //从中间表中，添加数据记录
        adminApply.setAdminLevel(body.getAdminLevel());
        adminApply.setRemark(body.getRemark());
        userRoleService.save(adminApply);
    }

    @Override
    public void addApply(AdminApplyRo ro) {
        //先将url 整合：
        String zhengmingcailiaoURL = String.join(",", ro.getZhengmingcailiao_url());
        //复制：
        AdminApply db = new AdminApply();
        BeanUtils.copyProperties(ro, db);
        db.setStatus(0); //默认未通过
        db.setZhengmingcailiaoUrl(zhengmingcailiaoURL);//设置url合集
        //保存到数据库中
        userRoleService.save(db);
    }
}
