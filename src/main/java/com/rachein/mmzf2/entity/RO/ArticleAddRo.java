package com.rachein.mmzf2.entity.RO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/20
 * @Description
 */
@Data
public class ArticleAddRo {
    List<ArticleRo> articles;
}
