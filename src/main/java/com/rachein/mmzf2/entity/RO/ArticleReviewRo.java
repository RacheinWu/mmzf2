package com.rachein.mmzf2.entity.RO;

import lombok.Data;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/26
 * @Description
 */
@Data
public class ArticleReviewRo {
    private Long articleId;
    private Boolean result;
    private String remark;
}
