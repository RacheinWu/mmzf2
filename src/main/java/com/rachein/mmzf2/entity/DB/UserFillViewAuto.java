package com.rachein.mmzf2.entity.DB;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/22
 * @Description 用于前端全自动显示的数据封装类
 */
@Data
public class UserFillViewAuto {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String description;
    private String action;
    private String obj;
    private Boolean showPicker;

}
