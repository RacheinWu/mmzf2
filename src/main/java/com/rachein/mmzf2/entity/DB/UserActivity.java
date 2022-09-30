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
 * 用户and活动报名关系
 * </p>
 *
 * @author 吴远健
 * @since 2022-09-30
 */
@Getter
@Setter
@TableName("user_activity")
@ApiModel(value = "UserActivity对象", description = "用户and活动报名关系")
public class UserActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("用户微信号")
    @TableField("open_id")
    private String openId;

    @ApiModelProperty("活动id")
    @TableField("activity_id")
    private Long activityId;

    @ApiModelProperty("默认为1：正常")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("报名时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;


}
