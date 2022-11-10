package com.rachein.mmzf2.core.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.rachein.mmzf2.exception.GlobalException;
import com.rachein.mmzf2.result.CodeMsg;
import com.rachein.mmzf2.utils.AccessTokenUtil;
import com.rachein.mmzf2.utils.HttpRequestUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/27
 * @Description
 */
@Service
@Slf4j
public class VXServiceImpl {

    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.appsecret}")
    private String appsecret;

    public void login(String url) {

//        String vx_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=" + url + "&response_type=code&scope=snsapi_userinfo"+ "&state=STATE#wechat_redirect";
    }


    public SaTokenInfo getWebACTokenByCode (String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid +"&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code";
        SaTokenInfo tokenInfo = null;
        try {
            System.out.println(code);
            Response response = HttpRequestUtils.get(url);
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                System.out.println(responseBody);
                String openid = JSON.parseObject(responseBody).getString("openid");
                //sa-token登录：
                StpUtil.login(openid);
                tokenInfo = StpUtil.getTokenInfo();
                log.info(openid + "已登录!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException(CodeMsg.USER_LOGIN_ERROR);
        }
        return tokenInfo;
    }

    public String uploadCover(File file) {
        String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="+ AccessTokenUtil.getToken() +"&type=thumb";
        Response response = HttpRequestUtils.post(url, file, "file");
        try {
            String response_json = response.body().string();
            log.info(response_json);
            return JSON.parseObject(response_json).getString("media_id");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
