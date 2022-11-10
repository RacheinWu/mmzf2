package com.rachein.mmzf2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/26
 * @Description
 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Value("${path.file.local}")
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("res/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("i/**").addResourceLocations("file:" + path);
    }
}
