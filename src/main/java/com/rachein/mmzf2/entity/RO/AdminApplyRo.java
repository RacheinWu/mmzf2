package com.rachein.mmzf2.entity.RO;

import lombok.Data;

import java.util.List;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/3
 * @Description
 */
@Data
public class AdminApplyRo {
    private String remark;
    private String openid;
    private Integer adminLevel;
    private List<String> zhengmingcailiao_url;//证明材料图片地址
    private String suozaidanwei;//所在单位
    private String danweizhiwu;//单位职务
}
