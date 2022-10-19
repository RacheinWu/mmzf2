package com.rachein.mmzf2.core.service;

import java.util.List;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/19
 * @Description
 */
public interface IVXService {

    /**
     * 根据openId进行推送推文
     */
    Boolean articleSendByOpenId(List<String> openIds);

    /**
     * 群发推文
     */
    Boolean articleSend();

    /**
     * 发送消息
     * @Param modelId -> 消息模板编号
     * @Param openIds -> 发送对象
     */
    Boolean messageSendByOpenIds(String modelId, List<String> openIds);

    /**
     * 上传推文封面
     * @param url
     * @return 微信服务器url
     */
    Boolean articleCoverUpload(String url);

    /**
     * 上传图文所需材料
     * @param url
     * @return 微信服务器url
     */
    String articleMaterialUpload(String url);

    /**
     * 上传草稿
     * @return media_id
     */
    String DraftSave(String draftId);


}
