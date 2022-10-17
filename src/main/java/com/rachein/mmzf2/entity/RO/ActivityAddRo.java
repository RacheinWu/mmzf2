package com.rachein.mmzf2.entity.RO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/5
 * @Description
 */
@Data
public class ActivityAddRo {
    private String title;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStart;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeEnd;
    private String address;
    private String remark;
}
