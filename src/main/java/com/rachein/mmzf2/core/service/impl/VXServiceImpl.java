package com.rachein.mmzf2.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.rachein.mmzf2.utils.HttpRequestUtils;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/27
 * @Description
 */
@Service
public class VXServiceImpl {

    @Value("wechat.appid")
    private String appid;

    @Value("wechat.appsecret")
    private String appsecret;

    public void login(String url) {

//        String vx_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=" + url + "&response_type=code&scope=snsapi_userinfo"+ "&state=STATE#wechat_redirect";
    }


    public void getWebACTokenByCode (String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid +"&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code";
        try {
            Response response = HttpRequestUtils.get(url);
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                System.out.println(responseBody);
                //sa-token登录：

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
