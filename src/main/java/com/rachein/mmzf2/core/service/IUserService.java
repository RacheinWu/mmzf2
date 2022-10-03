package com.rachein.mmzf2.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rachein.mmzf2.entity.DB.User;
import com.rachein.mmzf2.entity.RO.UserUpdateRo;
import com.rachein.mmzf2.entity.VO.UserVo;

import java.util.List;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/20
 * @Description
 */
public interface IUserService extends IService<User> {
    void updateInfo(String openId, UserUpdateRo info);

    Object getByOpenId(String openId);

    List<UserVo> listUserByIds(List<String> ids);
}
