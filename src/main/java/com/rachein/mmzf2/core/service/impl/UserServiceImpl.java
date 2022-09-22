package com.rachein.mmzf2.core.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.mmzf2.core.mapper.UserMapper;
import com.rachein.mmzf2.core.service.IUserService;
import com.rachein.mmzf2.entity.DB.User;
import com.rachein.mmzf2.entity.RO.UserUpdateRo;
import org.springframework.beans.BeanUtils;

import java.sql.Wrapper;
import java.util.Objects;

/**
 * @author 物联网工程系 ITAEM 唐奕泽
 * @Description
 * @date 2022/9/21 21:03
 */
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {
    @Override
    public void updateInfo(String openId, UserUpdateRo info) {
        User user = lambdaQuery().eq(User::getOpenid, openId).one();
        if (!Objects.isNull(user)){
            BeanUtils.copyProperties(info,user);
        }
        updateById(user);
    }
}
