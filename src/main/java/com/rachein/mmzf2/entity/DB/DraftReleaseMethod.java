package com.rachein.mmzf2.entity.DB;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.rachein.mmzf2.entity.enums.IdentityEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 推文发布方式
 * </p>
 *
 * @author 吴远健
 * @since 2022-11-09
 */
@Getter
@Setter
@TableName("draft_release_method")
@ApiModel(value = "DraftReleaseMethod对象", description = "推文发布方式")
public class DraftReleaseMethod implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("推文id")
    @TableField("draft_id")
    private Long draftId;

    @ApiModelProperty("年龄上限")
    @TableField("age_upper")
    private Integer ageUpper;

    @ApiModelProperty("年龄下限")
    @TableField("age_lower")
    private Integer ageLower;

    @ApiModelProperty("专业")
    @TableField("major")
    private String major;

    @ApiModelProperty("身份 0：高一二同学 1：高三学生 2：大学生")
    @TableField(exist = false)
    private IdentityEnum identity;

    @TableField("identity")
    private String identity_list;

}
