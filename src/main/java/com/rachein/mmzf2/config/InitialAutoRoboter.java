package com.rachein.mmzf2.config;

import com.rachein.mmzf2.core.service.IUserService;

import com.rachein.mmzf2.core.service.impl.UserQueue;
import com.rachein.mmzf2.redis.RedisService;
import com.rachein.mmzf2.utils.AccessTokenUtil;
import com.rachein.mmzf2.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
@Slf4j
public class InitialAutoRoboter {


    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.appsecret}")
    private String appsecret;
    @Value("${wechat.token-url}")
    private String getTokenUrl;
    @Value("${wechat.user-info-url}")
    private String userInfoUrl;

    @Value("${path.file.from}")
    private String from_path;
    @Value("${path.file.local}")
    private String local_path;
    @Value("${path.url.local}")
    private String local_url;
    @Value("${path.reflect.prefix}")
    private String reflect_prefix;

    @Autowired
    private RedisService redisService;

    @Autowired
    private IUserService userService;

    @Bean
    public void run() {
        log.info(">>>>>>>>>>>>>>>>> 读取配置中...");
        System.out.println("***-> appid = " + appid);
        System.out.println("***-> appsecret = " + appsecret);
        System.out.println("***-> getTokenUrl = " + getTokenUrl);
        System.out.println("***-> userInfoUrl = " + userInfoUrl);
        AccessTokenUtil.appsecret = appsecret;
        AccessTokenUtil.getTokenUrl = getTokenUrl;
        AccessTokenUtil.appid = appid;
        AccessTokenUtil.redisService = redisService;
        UserQueue.userService = userService;
        FileUtils.from_path = from_path;
        FileUtils.local_path = local_path;
        FileUtils.local_url = local_url;
        FileUtils.reflect_path_prefix = reflect_prefix;

        UserQueue.getUserInfoUrl = userInfoUrl;
        log.info("<<<<<<<<<<<<<<<<<< 已从配置文件中读取配置!");
        //
        UserQueue.listen();
        log.info("<<<<<<<<<<<<<<<<<< 用户监听队列已开启....");

    }
}
