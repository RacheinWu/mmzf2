package com.rachein.mmzf2.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rachein.mmzf2.entity.DB.StudentHighInfo;
import com.rachein.mmzf2.entity.DB.StudentLow12Info;
import com.rachein.mmzf2.entity.DB.StudentLow3Info;
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

    void updateInfoToHighStudent(StudentHighInfo info);

    void updateInfoToLowStudent12(StudentLow12Info info);

    void updateInfoToLowStudent3(StudentLow3Info info);

    List<User> listUser();
}
