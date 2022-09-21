package com.rachein.mmzf2.utils;

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
        String str = "<xml>\n" +
                "  <ToUserName><![CDATA[%s]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[%s]]></FromUserName>\n" +
                "  <CreateTime>%d</CreateTime>\n" +
                "  <MsgType><![CDATA[%s]]></MsgType>\n" +
                "  <Content><![CDATA[%s]]></Content>\n" +
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
}
