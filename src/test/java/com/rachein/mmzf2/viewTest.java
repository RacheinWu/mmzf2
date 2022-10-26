package com.rachein.mmzf2;

import com.rachein.mmzf2.core.mapper.ViewAutoUserFillMapper;
import com.rachein.mmzf2.entity.DB.StudentHighInfo;
import io.swagger.annotations.ApiModelProperty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/22
 * @Description
 */
@SpringBootTest
class viewTest {

    @Test
    void reflect() {
        //反射测试
        Field[] fields = StudentHighInfo.class.getDeclaredFields();
        System.out.println(fields.length);
        for (Field field : fields) {
//            System.out.println(field.getAnnotation(ApiModelProperty.class).value());
            ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
            if (!Objects.isNull(annotation)) {
                System.out.println(annotation.value());
            }

        }
    }



    @Test
    void insert() {
    }

    @Autowired
    private ViewAutoUserFillMapper userFillMapper;

    @Test
    void uodate() {
//        userFillMapper.up

    }

    @Test
    void StringFormat() {
        String article = "<p>这是标题</p><div><img src=\"%s\"></div><div><img src=\"%s\"></div>";
        String format = String.format(article, "http://itaem.cn/1adnwud/123da.jpg", "http://itaem.cn/1adnwud/123da.jpg");
        System.out.println(format);

    }

}
