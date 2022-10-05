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
 * 活动
 * </p>
 *
 * @author 吴远健
 * @since 2022-09-30
 */
@Getter
@Setter
@TableName("t_activity")
@ApiModel(value = "Activity对象", description = "活动")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("活动名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("活动开始时间")
    @TableField("time_start")
    private LocalDateTime timeStart;

    @ApiModelProperty("活动结束时间")
    @TableField("time_end")
    private LocalDateTime timeEnd;

    @ApiModelProperty("活动地点")
    @TableField("address")
    private String address;

    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty("创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("默认1：正常")
    @TableField("status")
    private Integer status;

    private Long activityId;


}
