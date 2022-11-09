package com.rachein.mmzf2.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/11/9
 * @Description
 */
@Getter
public enum DraftStateEnum {
    DRAWING(0, "未发布"),
    PADDING(1, "待审核"),
    RELEASED(2, "已发布");

    @EnumValue
    private final Integer val;

    @JsonValue
    private final String desc;

    DraftStateEnum(Integer val, String desc) {
        this.val = val;
        this.desc = desc;
    }
}
