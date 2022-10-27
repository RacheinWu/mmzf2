package com.rachein.mmzf2.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rachein.mmzf2.entity.DB.UserFillViewAuto;
import com.rachein.mmzf2.entity.VAO.UserFill;
import com.rachein.mmzf2.entity.VAO.UserTableVAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/23
 * @Description
 */
@Mapper
public interface ViewAutoUserFillMapper extends BaseMapper<UserFillViewAuto> {

    @Select("select * from user_fill_vo where  obj = ${obj}")
    List<UserFill> list(@Param("obj") Integer id);

    @Select("select * from table_user_vo")
    List<UserTableVAO> listUserTable();

}
