package com.rachein.mmzf2.entity.DB;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 文件
 * </p>
 *
 * @author 吴远健
 * @since 2022-09-19
 */
@Getter
@Setter
@TableName("t_file")
@ApiModel(value = "File对象", description = "文件")
public class FileDB implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("文件后缀名 .xxx")
    @TableField("suffix")
    private String suffix;

    @ApiModelProperty("保存地址")
    @TableField("path")
    private String path;

    @ApiModelProperty("UUID保存的名字")
    @TableField("name")
    private String name;

    @ApiModelProperty("上传时的名字")
    @TableField("old_name")
    private String oldName;

    @ApiModelProperty("枚举类")
    @TableField("type")
    private String type;

    @ApiModelProperty("字节大小")
    @TableField("size")
    private Long size;

    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;


}
