package com.rachein.mmzf2.entity.RO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/11/9
 * @Description
 */
@Data
public class DraftCheckRo {
    @JsonProperty("draft_id")
    private String draftId;
    private Boolean result;
    private String remark;
}
