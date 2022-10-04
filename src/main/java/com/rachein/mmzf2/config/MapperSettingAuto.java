package com.rachein.mmzf2.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 计算机系 ITAEM 吴远健
 * @Description AOP操作，一步到位，不用到每个DB类中进行定义两个时间段
 * @date 2022/5/27 7:42
 */
@Component
@Slf4j
public class MapperSettingAuto implements MetaObjectHandler {

    /**
     * 插入时间
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //设置时间: （也可以通过DB类进行自动注解开发）
//        log.info("设置时间");
        this.setFieldValByName("gmtCreate", LocalDateTime.now(), metaObject);
        this.setFieldValByName("gmtModified", LocalDateTime.now(), metaObject);
    }

    /**
     * 更新时间
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }
}