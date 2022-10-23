package com.rachein.mmzf2.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rachein.mmzf2.entity.DB.UserFillViewAuto;
import com.rachein.mmzf2.entity.VAO.UserFill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/23
 * @Description
 */
@Mapper
public interface ViewAutoUserFillMapper extends BaseMapper<UserFillViewAuto> {

    @Select("select * from user_fill_vo")
    List<UserFill> list();
}
