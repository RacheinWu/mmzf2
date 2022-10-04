package com.rachein.mmzf2.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/4
 * @Description
 */
public class AdminAppleExcel {
    @ExcelProperty("微信号")
    private String openId;
    @ExcelProperty("真实名称")
    private String nickname;
    @ExcelProperty("审核状态")
    private Boolean status;
    @ExcelProperty("性别")
    private String gender;
    @ExcelProperty("手机号")
    private String phone;
    @ExcelProperty("备注")
    private String remark;
    @ExcelProperty("申请时间")
    private String gmtModified;
    @ExcelProperty("所在单位")
    private String suozaidanwei;//所在单位
    @ExcelProperty("单位职务")
    private String danweizhiwu;//单位职务
}
