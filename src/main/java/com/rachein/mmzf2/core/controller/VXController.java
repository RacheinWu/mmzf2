package com.rachein.mmzf2.core.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.rachein.mmzf2.core.service.IUserService;
import com.rachein.mmzf2.core.service.impl.UserQueue;
import com.rachein.mmzf2.core.service.impl.VXServiceImpl;
import com.rachein.mmzf2.result.Result;
import com.rachein.mmzf2.utils.MessageUtil;
import com.rachein.mmzf2.utils.XmlUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
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

    @Value("${wechat.appid}")
    private String appid;

//    @Value("${wechat.appsecret}")
//    private String appsecret;

    @Autowired
    private VXServiceImpl vxService;


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
                reply = "感谢你的订阅<a href=\"http://cn-hk-nf-1.natfrp.cloud:24669/i/index.html\">x</a>";
                log.info(fromUserName);
                UserQueue.QUEUE.push(fromUserName);
            }else if (event.equals("unsubscribe")){
                log.info("触发不再关注事件");
            }
        }
        return MessageUtil.formatMsg(fromUserName, toUserName, 1, "text", reply);
    }

    @ApiOperation("返回微信认证所需参数")
    @GetMapping("/profile")
    public Result<Map<String, String>> profile() {
        //appid	是	公众号的唯一标识
        //获取公众号的appsecret
        //scope = snsapi_base
        //response_type	是	返回类型，请填写code
        Map<String, String> map = new HashMap<>();
        map.put("appid", appid);
        map.put("scope", "snsapi_base");
        map.put("response_type", "code");
        return Result.success(map);
    }

    @ApiOperation("用code获取accessToken")
    @PostMapping("/get/access_token")
    public Result<Object> getWebAccessToken(@RequestBody String code) {
        String code2 = code.replace("=", "");
        log.info(code2);

        SaTokenInfo tokenInfo = vxService.getWebACTokenByCode(code2);
        return Result.success(tokenInfo);

    }

    @GetMapping("/login")
    public boolean login2() {
        StpUtil.login("1");
        return StpUtil.isLogin();
    }



//    @GetMapping("/login")
//    public void login(@RequestParam("url") String url) {
//        vxService.login(url);
//    }

}
