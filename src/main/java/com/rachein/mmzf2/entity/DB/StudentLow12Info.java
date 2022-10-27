package com.rachein.mmzf2.entity.DB;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
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
@Getter
@Setter
@TableName("student_low12_info")
@ApiModel(value = "StudentLow12Info对象", description = "")
public class StudentLow12Info implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("微信联动")
    @TableField("openid")
    private String openid;

    @ApiModelProperty("个人照片/头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("真实姓名")
    @TableField("nickname")
    private String nickname;

    @TableField("gender")
    private Integer gender;

    @ApiModelProperty("名族")
    @TableField("minzu")
    private String minzu;

    @ApiModelProperty("政治面貌")
    @TableField("zhengzhimianmao")
    private String zhengzhimianmao;

    @ApiModelProperty("联系方式（手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("籍贯")
    @TableField("address")
    private String address;

    @ApiModelProperty("当前院校")
    @TableField("school")
    private String school;

    @ApiModelProperty("年级")
    @TableField("grade")
    private String grade;

    @ApiModelProperty("社团")
    @TableField("shetuan")
    private String shetuan;

    @ApiModelProperty("任职")
    @TableField("renzhi")
    private String renzhi;

    @ApiModelProperty("兴趣爱好、特长")
    @TableField("hobby")
    private String hobby;

    @ApiModelProperty("目标高校")
    @TableField("aim_school")
    private String aimSchool;

    @ApiModelProperty("目标专业")
    @TableField("mubiao_major")
    private String mubiaoMajor;

    @ApiModelProperty("是否了解近年高考形势、政策（是/否）")
    @TableField("shifou_liaojie_gaokao_xingshi")
    private Integer shifouLiaojieGaokaoXingshi;

    @ApiModelProperty("是否需要学习经验分享类讲座（是/否）")
    @TableField("shifou_xuyao_xuexi_jinyan_fenxiang")
    private Integer shifouXuyaoXuexiJinyanFenxiang;

    @ApiModelProperty("默认1 正常")
    @TableField("status")
    private Integer status;

    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;


}
