package com.rachein.mmzf2.utils;

import com.rachein.mmzf2.entity.DB.FileDB;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/20
 * @Description 文件解析、上传工具类
 */
public class FileUtils {

    /**
     * 保存到数据库中
     * @param file -> DB
     * @return 是否保存成功
     */
    public static boolean save(FileDB file) {return true;}

    /**
     * 判断文件的大小，类型是否有误
     * @param file
     * @return 是否正确
     */
    public static boolean judge(MultipartFile file) {return true;}



}
