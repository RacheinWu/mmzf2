package com.rachein.mmzf2.entity.DB;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 吴远健
 * @since 2022-10-03
 */
@Getter
@Setter
@TableName("user_admin_apply")
@ApiModel(value = "管理员申请表", description = "")
public class AdminApply implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("openid")
    private String openid;

    @TableField("admin_level")
    private Integer adminLevel;

    @ApiModelProperty("身份审核状态 默认1 通过")
    @TableField("status")
    private Integer status;

    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    private String remark;

    private String suozaidanwei;//所在单位

    private String danweizhiwu;//单位职务

    @TableField("zhengmingcailiao_url")
    private String zhengmingcailiaoUrl;


}
