package com.rachein.mmzf2.core.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/4
 * @Description
 */
@Api(tags = "Excel导出")
@RestController
@RequestMapping("excel")
public class ExcelController {

    /**
     * @param resource 导出的数据是属于？
     */
    @GetMapping("/output/{resource}")
    public void output(@PathVariable String resource) {


    }
}
