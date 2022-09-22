package com.rachein.mmzf2.utils;

import com.rachein.mmzf2.core.service.IFileService;
import com.rachein.mmzf2.entity.DB.FileDB;
import com.rachein.mmzf2.exception.GlobalException;
import com.rachein.mmzf2.result.CodeMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/20
 * @Description 文件解析、上传工具类
 */
@Transactional
@Slf4j
public class FileUtils {

    public static String from_path;
    public static String local_path;
    public static String local_url;
    public static String reflect_path_prefix;
    private static IFileService fileService;


    /**
     * 保存到本地以及数据库中
     * @param
     * @param file -> DB
     * @return 保存后生成的
     */
    public static FileDB save(MultipartFile file, String vx_url, String media_id) {
        //是否存在文件
        if (file == null || file.getContentType().equals("application/octer-stream")) throw new GlobalException(CodeMsg.FILE_EMPTY);
        //检测文件大小
        long size = file.getSize();

        //originalName:
        String originalFilename = file.getOriginalFilename();

        //获取后缀
        String extension = "." + FilenameUtils.getExtension(originalFilename);

        //检测文件类型
        String file_type = file.getContentType();

        //newFileName:
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                UUIDUtil.uuid().substring(6) + extension;

        //根据xx进行上传文件:
        String formPath = from_path;
        String dataDirPath = local_path + formPath;
        File dataDir = new File(dataDirPath);//document
        if (!dataDir.exists()) dataDir.mkdirs();
        log.info("存储的地址为：" + dataDirPath);
        //保存到本地:
        try {
            file.transferTo(new File(dataDir,newFileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException(CodeMsg.FILE_ERROR);
        }

        //database-save：
        String network_path = local_url + reflect_path_prefix  + formPath + "/" + newFileName;
        log.info("网络url = " + network_path);
        FileDB fileDB = new FileDB();
        fileDB.setName(newFileName);
        fileDB.setOldName(originalFilename);
        fileDB.setSize(size);
        fileDB.setType(file_type);
        fileDB.setLocalUrl(network_path);
        fileDB.setSuffix(extension);
        fileDB.setVx_url(vx_url);
        fileDB.setMediaId(media_id);
        log.info(fileDB.toString());
        return fileDB;
    }



    /**
     * 判断文件的大小，类型是否有误
     * @param file
     * @param targetSize
     * @param targetType
     * @return 是否正确
     */
    public static boolean judge(MultipartFile file, Long targetSize, String targetType) {
        //是否存在文件
        if (file == null || file.getContentType().equals("application/octer-stream")) throw new GlobalException(CodeMsg.FILE_EMPTY);
        //检测文件大小
        long size = file.getSize();
        System.out.println(size);
        if (size > 20024) throw new GlobalException(CodeMsg.FILE_MEMORY_OVER);

        //originalName:
        String originalFilename = file.getOriginalFilename();

        //获取后缀
        String extension = "." + FilenameUtils.getExtension(originalFilename);

        //检测文件类型
        String file_type = file.getContentType();
        return true;
    }



}
