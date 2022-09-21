package com.rachein.mmzf2.core.controller;

import ch.qos.logback.core.joran.spi.XMLUtil;
import com.rachein.mmzf2.core.service.IUserService;
import com.rachein.mmzf2.core.service.impl.UserQueue;
import com.rachein.mmzf2.utils.MessageUtil;
import com.rachein.mmzf2.utils.XmlUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOError;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/20
 * @Description
 */
@RestController
@RequestMapping
@Slf4j
public class VXController {

    @Autowired
    private IUserService userService;

    //微信认证测试
    @GetMapping("/wechat")
    public String check(String signature, String timestamp, String nonce, String echostr){
        System.out.println(signature);
        return echostr;
    }

    @PostMapping(value = "/wechat" , produces = {"application/xml;charset=utf-8"})
    public Object doRun(HttpServletRequest request)throws IOException {
        Map<String, String> map = XmlUtil.getMap(request.getInputStream());
        String msgType = map.get("MsgType");
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        String reply = "";

        if (msgType.equals("event")){
            String event = map.get("Event");
            if (event.equals("subscribe")){
                reply = "感谢你的订阅";
                log.info(fromUserName);

//                UserQueue.QUEUE.push(fromUserName);
            }else if (event.equals("unsubscribe")){
                reply = "触发不再关注事件";
//                userService.removeByOpenId(fromUserName);
            }
        }

        return MessageUtil.formatMsg(fromUserName, toUserName, 1, "text", reply);
    }

}
