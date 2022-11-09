package com.rachein.mmzf2.entity.RO;

import com.rachein.mmzf2.entity.DTO.tag.TagDTO;
import com.rachein.mmzf2.entity.enums.DraftMethodEnum;
import lombok.Data;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/11/9
 * @Description 推文申请发布RO类
 */
@Data
public class DraftApplicationRo {
    private Long draft_id;
    private DraftMethodEnum method;
    private TagDTO tag;
}
