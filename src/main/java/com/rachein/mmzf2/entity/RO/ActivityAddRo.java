package com.rachein.mmzf2.entity.RO;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/5
 * @Description
 */
@Data
public class ActivityAddRo {
    private String title;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private String address;
    private String remark;
}
