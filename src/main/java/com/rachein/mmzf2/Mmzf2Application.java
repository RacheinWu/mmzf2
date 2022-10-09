package com.rachein.mmzf2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.rachein.mmzf2.core.mapper")
@EnableScheduling //定时器注解
public class Mmzf2Application {

    public static void main(String[] args) {
        SpringApplication.run(Mmzf2Application.class, args);
    }
}
