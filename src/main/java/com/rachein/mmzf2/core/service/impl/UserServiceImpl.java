package com.rachein.mmzf2.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.mmzf2.core.mapper.UserMapper;
import com.rachein.mmzf2.core.service.IStudentHighInfoService;
import com.rachein.mmzf2.core.service.IStudentLow12InfoService;
import com.rachein.mmzf2.core.service.IStudentLow3InfoService;
import com.rachein.mmzf2.core.service.IUserService;
import com.rachein.mmzf2.entity.DB.StudentHighInfo;
import com.rachein.mmzf2.entity.DB.StudentLow12Info;
import com.rachein.mmzf2.entity.DB.StudentLow3Info;
import com.rachein.mmzf2.entity.DB.User;
import com.rachein.mmzf2.entity.RO.UserUpdateRo;
import com.rachein.mmzf2.entity.VO.UserVo;
import com.rachein.mmzf2.exception.GlobalException;
import com.rachein.mmzf2.result.CodeMsg;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 物联网工程系 ITAEM 唐奕泽
 * @Description
 * @date 2022/9/21 21:03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {

    @Autowired
    private IStudentHighInfoService studentHighInfoService;

    @Autowired
    private IStudentLow3InfoService studentLow3InfoService;

    @Autowired
    private IStudentLow12InfoService studentLow12InfoService;


    @Override
    public void updateInfo(String openId, UserUpdateRo info) {

    }

    @Override
    public Object getByOpenId(String openId) {
        //先根据openId 在user表中查询，select-> category_id
        User user = lambdaQuery().eq(User::getOpenid, openId)
                .select(User::getCategoryId)
                .one();
        if (Objects.isNull(user)) throw new GlobalException(CodeMsg.USER_NOT_FOUND);
        //switch 语句，根据category_id 选取数据库进行查询info
        switch (user.getCategoryId()) {
            case 0: {


            }
            case 1: {

            }
            case 2: {

            }
            default: {
                break;
            }
        }
        //拼接sql
        //根据openId在这表中获取信息
        return null;
    }

    @Override
    public List<UserVo> listUserByIds(List<String> ids) {
        List<UserVo> userVos = new ArrayList<>();
        for (String id : ids) {
            User db = lambdaQuery().eq(User::getOpenid, id)
                    .one();
            UserVo vo = new UserVo();
            BeanUtils.copyProperties(db, vo);
            userVos.add(vo);
        }
        return userVos;
    }

    @Override
    public void updateInfoToHighStudent(String openId, StudentHighInfo info) {
        //用户信息更新 || 添加
        studentHighInfoService.saveOrUpdate(info);
        //等待管理员审核
    }

    @Override
    public void updateInfoToLowStudent12(String openId, StudentLow12Info info) {
        //用户信息更新 || 添加
        studentLow12InfoService.saveOrUpdate(info);
        //等待管理员审核
    }

    @Override
    public void updateInfoToLowStudent3(String openId, StudentLow3Info info) {
        //用户信息更新 || 添加
        studentLow3InfoService.saveOrUpdate(info);
        //等待管理员审核
    }
}
