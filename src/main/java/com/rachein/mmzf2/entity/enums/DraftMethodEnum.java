package com.rachein.mmzf2.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/11/9
 * @Description 发布方式枚举
 */
@Getter
public enum DraftMethodEnum {
    ALL(0, "群发"),
    TAG(1, "标签");

    @EnumValue
    private final Integer val;

    @JsonValue
    private final String desc;

    DraftMethodEnum(Integer val, String desc) {
        this.val = val;
        this.desc = desc;
    }
}
