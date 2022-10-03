package com.rachein.mmzf2.core.service;

import com.rachein.mmzf2.entity.DB.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rachein.mmzf2.entity.DB.UserRole;
import com.rachein.mmzf2.entity.RO.AdminApplyRo;
import com.rachein.mmzf2.entity.VO.UserVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 吴远健
 * @since 2022-09-30
 */
public interface IRoleService extends IService<Role> {

    List<UserVo> listAdmin(Integer level);

    void checkApplyIIByMid(Long userRoleId, Boolean result);

    List<UserRole> listAdminApply();

    void grantII(AdminApplyRo body);
}
