package com.rachein.mmzf2.entity.DTO.vx.msg;

import lombok.Data;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/11/10
 * @Description
 */
@Data
public class ArticleVX {
    private String thumb_media_id;
    private String author;
    private String title;
    private String content_source_url;
    private String content;
    private String digest;
    private Integer show_cover_pic;
    private Integer need_open_comment;
    private Integer only_fans_can_comment;
}
