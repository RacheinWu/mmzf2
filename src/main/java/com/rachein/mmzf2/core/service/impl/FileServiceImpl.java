package com.rachein.mmzf2.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.mmzf2.core.mapper.FileMapper;
import com.rachein.mmzf2.core.service.IFileService;
import com.rachein.mmzf2.entity.DB.FileDB;
import com.rachein.mmzf2.exception.GlobalException;
import com.rachein.mmzf2.result.CodeMsg;
import com.rachein.mmzf2.utils.UUIDUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 文件 服务实现类
 * </p>
 *
 * @author 吴远健
 * @since 2022-09-19
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileDB> implements IFileService {

    @Value("${path.file.from}")
    private String from_path;

    @Value("${path.file.local}")
    private String local_path;

    @Value("${path.url.local}")
    private String local_url;

    @Override
    public String upload(MultipartFile file, String type, String appid) {
        //是否存在文件
        if (file == null || file.getContentType().equals("application/octer-stream")) throw new GlobalException(CodeMsg.FILE_EMPTY);
        //检测文件大小
        long size = file.getSize();
        if (size > 500E6) throw new GlobalException(CodeMsg.FILE_MEMORY_OVER);
        //originalName:
        String originalFilename = file.getOriginalFilename();
        //获取后缀
        String extension = "." + FilenameUtils.getExtension(originalFilename);
        //检测文件类型
        String file_type = file.getContentType();

        //newFileName:
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                UUIDUtil.uuid().substring(6) + extension;

        //根据学号进行上传文件:
        String formPath = from_path + appid;
        String dataDirPath = local_path + formPath;
        File dataDir = new File(dataDirPath);//document
        if (!dataDir.exists()) dataDir.mkdirs();

        //upload:
        try {
            file.transferTo(new File(dataDir,newFileName));
        } catch (IOException e) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        //database-save：

        String network_path = local_url + "\\" + formPath + "\\" + newFileName;

        FileDB fileDB = new FileDB();
        fileDB.setName(newFileName);
        fileDB.setOldName(originalFilename);
        fileDB.setSize(size);
        fileDB.setType(type);
        fileDB.setLocalUrl(network_path);
        fileDB.setSuffix(extension);
        save(fileDB);

        //进行上传到
        return network_path;
    }

    @Override
    public void batchUpload(List<MultipartFile> files, String type, String appid) {
    }
}
