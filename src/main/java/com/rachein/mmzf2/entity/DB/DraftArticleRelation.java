package com.rachein.mmzf2.entity.DB;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/21
 * @Description
 */
@Data
@TableName("draft_article")
public class DraftArticleRelation {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long draftId;
    private Long articleId;
    private Integer index;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

}
