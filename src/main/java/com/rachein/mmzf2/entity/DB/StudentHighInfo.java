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
 * 大学生信息
 * </p>
 *
 * @author 吴远健
 * @since 2022-09-30
 */
@Getter
@Setter
@TableName("student_high_info")
@ApiModel(value = "StudentHighInfo对象", description = "大学生信息")
public class StudentHighInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    @ApiModelProperty("兴趣爱好、特长")
    @TableField("hobby")
    private String hobby;

    @ApiModelProperty("是否了解家乡人才引进政策（是/否）")
    @TableField("shifou_liaojie_jiaxinagrencai_yinjinzhengce")
    private Integer shifouLiaojieJiaxinagrencaiYinjinzhengce;

    @ApiModelProperty("是否了解家乡发展状况（是/否）")
    @TableField("shifou_liaojie_jiaxinagfazhan_zhuangkuan")
    private Integer shifouLiaojieJiaxinagfazhanZhuangkuan;

    @ApiModelProperty("何时何地获得何种奖励或荣誉（自填）")
    @TableField("heshihedi_huode_hezhong_jiangli")
    private String heshihediHuodeHezhongJiangli;

    @TableField("remark")
    private String remark;

    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty("默认1 正常")
    @TableField("status")
    private Integer status;

    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty("就业岗位方向（选择 策划运营/质量工程师/研发/销售/管理/考公/事业编/设计/新媒体/教师/编程人员/其他")
    @TableField("job_direction")
    private String jobDirection;

    @ApiModelProperty("回乡意愿（选择 强烈/一般/无")
    @TableField("huixiang_yiyuan")
    private String huixiangYiyuan;

    @ApiModelProperty("就业情况（选择 已就业/待业）")
    @TableField("jiuye_qingkuang")
    private String jiuyeQingkuang;

    @ApiModelProperty("职业方向（选择  考公、考编/企业就业/创业）")
    @TableField("zhiye_direction")
    private String zhiyeDirection;

    @ApiModelProperty("专业名称（自填）")
    @TableField("major_name")
    private String majorName;

    @ApiModelProperty("专业大类（选择")
    @TableField("major_category")
    private String majorCategory;


}
