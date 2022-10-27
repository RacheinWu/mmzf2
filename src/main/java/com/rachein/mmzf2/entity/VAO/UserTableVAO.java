package com.rachein.mmzf2.entity.VAO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/27
 * @Description
 */
@Data
@TableName("table_user_vo")
public class UserTableVAO {
    private Integer id;
    private String name;
    private String description;
}
