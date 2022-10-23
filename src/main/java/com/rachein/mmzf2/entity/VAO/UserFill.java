package com.rachein.mmzf2.entity.VAO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/23
 * @Description
 */
@Data
@TableName("user_fill_vo")
public class UserFill {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("输入框提示词")
    private String placeholder;
    @ApiModelProperty("描述")
    private String description;

    @TableField("option_values_str")
    private String optionValuesStr;

    @ApiModelProperty("给谁填写的？")
    private String obj;

    @ApiModelProperty("单选框的values，只有当type = select的时候才有内容")
    @TableField(exist = false)
    private List<String> optionValues;
}
