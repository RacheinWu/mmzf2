package com.rachein.mmzf2.entity.DB;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/21
 * @Description
 */
@Data
public class Draft {
    @TableId(type = IdType.INPUT)
    private Long id;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
