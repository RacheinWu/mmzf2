package com.rachein.mmzf2.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/11/9
 * @Description
 */
@Getter
public enum IdentityEnum {
    I0(0, "高一二学生"),
    I1(1, "高三学生"),
    I2(2, "大学生");

    @EnumValue
    private final Integer val;

    @JsonValue
    private final String desc;

    IdentityEnum(Integer val, String desc) {
        this.val = val;
        this.desc = desc;
    }




}
