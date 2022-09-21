package com.rachein.mmzf2.entity.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/20
 * @Description
 */
@Data
@ApiModel("保存推文草稿之后所返回的类型")
public class ArticleResultVo {

    private String media_id;
    private String local_id;

}
