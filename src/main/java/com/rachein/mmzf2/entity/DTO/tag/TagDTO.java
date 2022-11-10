package com.rachein.mmzf2.entity.DTO.tag;

import com.rachein.mmzf2.entity.enums.IdentityEnum;
import lombok.Data;

import java.util.List;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/11/9
 * @Description
 */
@Data
public class TagDTO {
    private AgeDTO age;
    private List<IdentityEnum> identity;
    private String major;
}
