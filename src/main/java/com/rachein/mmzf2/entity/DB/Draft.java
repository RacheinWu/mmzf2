package com.rachein.mmzf2.entity.DB;

import com.baomidou.mybatisplus.annotation.*;
import com.rachein.mmzf2.entity.enums.DraftMethodEnum;
import com.rachein.mmzf2.entity.enums.DraftStateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 吴远健
 * @since 2022-09-30
 */
@Data
@TableName("t_draft")
@ApiModel(value = "推文对象", description = "")
public class Draft implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private DraftStateEnum state;

    private String imgCover;
    private LocalDateTime releaseTime;
    private LocalDateTime applicationTime;
    private DraftMethodEnum method;
    private String applicant;

    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
    @ApiModelProperty("申请人微信号")
    private String applicantId;


}
