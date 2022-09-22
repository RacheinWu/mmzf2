package com.rachein.mmzf2.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rachein.mmzf2.entity.DB.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 物联网工程系 ITAEM 唐奕泽
 * @Description
 * @date 2022/9/21 21:05
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
