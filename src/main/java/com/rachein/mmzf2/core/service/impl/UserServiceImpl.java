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
        User user = lambdaQuery().eq(User::getOpenid, openId).one();
        if (!Objects.isNull(user)){
            BeanUtils.copyProperties(info,user);
        }
        updateById(user);
    }

    @Override
    public Object getByOpenId(String openId) {
        //先根据openId 在user表中查询，select-> category_id
        //switch 语句，根据category_id 选取数据库进行查询info
        //拼接sql
        //根据openId在这表中获取信息
        return null;
    }

    @Override
    public List<UserVo> listUserByIds(List<String> ids) {
        return null;
    }

    @Override
    public void updateInfoToHighStudent(StudentHighInfo info) {
        //从user表中找到这个用户：
        User user = lambdaQuery().eq(User::getOpenid, info.getOpenid()).one();
        if (Objects.isNull(user)) throw new GlobalException(CodeMsg.USER_NOT_FOUND);
        //捆绑openid
        info.setOpenid(user.getOpenid());
        //设置状态为待审核：
        info.setStatus(0);
        //保存
        studentHighInfoService.save(info);
    }

    @Override
    public void updateInfoToLowStudent12(StudentLow12Info info) {
        //从user表中找到这个用户：
        User user = lambdaQuery().eq(User::getOpenid, info.getOpenid()).one();
        if (Objects.isNull(user)) throw new GlobalException(CodeMsg.USER_NOT_FOUND);
        //捆绑openid
        info.setOpenid(user.getOpenid());
        //设置状态为待审核：
        info.setStatus(0);
        //保存
        studentLow12InfoService.save(info);
    }

    @Override
    public void updateInfoToLowStudent3(StudentLow3Info info) {
        //从user表中找到这个用户：
        User user = lambdaQuery().eq(User::getOpenid, info.getOpenid()).one();
        if (Objects.isNull(user)) throw new GlobalException(CodeMsg.USER_NOT_FOUND);
        //捆绑openid
        info.setOpenid(user.getOpenid());
        //设置状态为待审核：
        info.setStatus(0);
        //保存
        studentLow3InfoService.save(info);
    }

    @Override
    public List<User> listUser() {
        List<User> list = lambdaQuery().list();
        return list;
    }
}
