package com.rachein.mmzf2.entity.DB;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author 吴远健
 * @since 2022-09-30
 */
@Getter
@Setter
@TableName("t_user")
@ApiModel(value = "User对象", description = "用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("微信联动")
    @TableField("openid")
    private String openid;

    @ApiModelProperty("用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。")
    @TableField("subscribe")
    private Integer subscribe;

    @ApiModelProperty("0:默认guest")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty("性别")
    @TableField("gender")
    private String gender;

    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty("真实名称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty("订阅时间")
    @TableField("subscribe_time")
    private String subscribeTime;

    @ApiModelProperty("用户类型id，是学生还是xx")
    @TableField("category_id")
    private Integer categoryId;

    @ApiModelProperty("是否补充了信息")
    private Boolean isFillInfo;

}
