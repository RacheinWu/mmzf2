package com.rachein.mmzf2.utils;

import com.rachein.mmzf2.entity.DTO.vx.msg.NewsContentDTO;
import com.rachein.mmzf2.entity.DTO.vx.msg.NewsContentDetailsDTO;
import com.rachein.mmzf2.entity.DTO.vx.msg.NewsReleaseDTO;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * 格式xml文件
 */
public class MessageUtil {

    /**
     * 格式化消息
     * @param toUser
     * @param fromUser
     * @param createTime
     * @param msgType
     * @param content
     * @return
     */
    public static String formatMsg(String toUser, String fromUser, int createTime, String msgType, String content) {
        String str = "<xml>" +
                "  <ToUserName><![CDATA[%s]]></ToUserName>" +
                "  <FromUserName><![CDATA[%s]]></FromUserName>" +
                "  <CreateTime>%d</CreateTime>" +
                "  <MsgType><![CDATA[%s]]></MsgType>" +
                "  <Content><![CDATA[%s]]></Content>" +
                "</xml>";

                //<xml>
                //  <ToUserName><![CDATA[toUser]]></ToUserName>
                //  <FromUserName><![CDATA[fromUser]]></FromUserName>
                //  <CreateTime>1348831860</CreateTime>
                //  <MsgType><![CDATA[text]]></MsgType>
                //  <Content><![CDATA[this is a test]]></Content>
                //  <MsgId>1234567890123456</MsgId>
                //  <MsgDataId>xxxx</MsgDataId>
                //  <Idx>xxxx</Idx>
                //</xml>

        return String.format(str, toUser, fromUser, createTime, msgType, content);
    }


    public static void applicationResult(String type, String result, String remark, String toUser, String templateId) {
        NewsContentDTO contentDTO = new NewsContentDTO(new NewsContentDetailsDTO(type),
                new NewsContentDetailsDTO(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm").format(LocalDateTime.now())),
                new NewsContentDetailsDTO(result),
                new NewsContentDetailsDTO( remark));
        //
        NewsReleaseDTO releaseDTO = new NewsReleaseDTO();
        releaseDTO.setUrl("");
        releaseDTO.setTouser(toUser);
        releaseDTO.setTemplate_id(templateId);
        releaseDTO.setData(contentDTO);
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + AccessTokenUtil.getToken();
        System.out.println(releaseDTO);
        try {
            Response post = HttpRequestUtils.post(url, releaseDTO);
            String string = post.body().string();
            System.out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
