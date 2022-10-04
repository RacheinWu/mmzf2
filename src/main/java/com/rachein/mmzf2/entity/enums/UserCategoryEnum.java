package com.rachein.mmzf2.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/4
 * @Description
 */
@Getter
public enum UserCategoryEnum {
    SENIOR_STUDENT(1, "高一高二学生"),
    JUNIOR_STUDENT(2, "高三学生"),
    COLLEGE_STUDENT(3, "大学生"),
    OTHER(4, "社会人士");

    @EnumValue
    private final Integer val;
    @JsonValue
    private final String name;

    UserCategoryEnum(Integer val, String name) {
        this.val = val;
        this.name = name;
    }
}
