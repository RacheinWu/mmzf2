package com.rachein.mmzf2.entity.VO;

import lombok.Data;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/3
 * @Description
 */
@Data
public class AdminApplyVo {
    private String openId;
    private String nickname;
    private Boolean status;
    private String gender;
    private String phone;
    private String remark;
    private String gmtModified;
}
