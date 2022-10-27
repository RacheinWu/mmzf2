package com.rachein.mmzf2.config;

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
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("i/**").addResourceLocations("classpath:/static/");

    }
}
