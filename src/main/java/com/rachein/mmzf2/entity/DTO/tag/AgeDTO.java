package com.rachein.mmzf2.entity.DTO.tag;

import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/11/9
 * @Description
 */
@Data
public class AgeDTO {
    //上限
    private Integer upper;
    //下限
    private Integer lower;
}
