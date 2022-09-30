package com.rachein.mmzf2.entity.DB;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 用户分类表：高中还是大学生还是社会人士
 * </p>
 *
 * @author 吴远健
 * @since 2022-09-30
 */
@Getter
@Setter
@TableName("user_category")
@ApiModel(value = "UserCategory对象", description = "用户分类表：高中还是大学生还是社会人士")
public class UserCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @ApiModelProperty("默认1 正常 / 0不正常")
    @TableField("status")
    private Integer status;


}
