package com.rachein.mmzf2.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.mmzf2.core.mapper.FileMapper;
import com.rachein.mmzf2.core.service.IArticleService;
import com.rachein.mmzf2.entity.DB.Article;
import com.rachein.mmzf2.entity.DB.FileDB;
import com.rachein.mmzf2.entity.RO.ArticleAddRo;
import com.rachein.mmzf2.entity.VO.ArticleResultVo;
import com.rachein.mmzf2.entity.VO.FileVo;
import com.rachein.mmzf2.utils.AccessTokenUtil;
import com.rachein.mmzf2.utils.FileUtils;
import com.rachein.mmzf2.utils.HttpRequestUtils;
import com.rachein.mmzf2.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 物联网工程系 ITAEM 唐奕泽
 * @Description
 *
 *
 * @date 2022/9/20 21:40
 */
@Slf4j
public class ArtiServiceImpl extends ServiceImpl<BaseMapper<Article>, Article> implements IArticleService {

    @Autowired
    FileMapper fileMapper;

    @Override
    public FileVo coverUpload(MultipartFile file) {
        String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="+ AccessTokenUtil.getToken() +"&type=thumb";
        String media_id;
        //校验数据
//        FileUtils.judge(file, 5000l, "img");
        //上传到微信服务器
        Response response = HttpRequestUtils.post(url, file, "media");
        try {
            String responseJson = response.body().string();
            media_id = JSON.parseObject(responseJson).getString("media_id");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //备份到本地服务器中
        FileVo save = FileUtils.save(file, null, media_id);
        return save;
    }

    @Override
    public FileVo materialUpload(MultipartFile file) {
        String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + AccessTokenUtil.getToken();
        String vx_url;
        //先进行检验文件
//        FileUtils.judge(file, 5000l, "img");
        //上传到微信服务器中
        Response response = HttpRequestUtils.post(url, file, "media");
        try {
            String responseJson = response.body().string();
            vx_url = JSON.parseObject(responseJson).getString("url");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //保存到本地硬盘,以及数据库
        FileVo save = FileUtils.save(file, vx_url, null);
        //返回链接
        return save;
    }

    @Override
    public ArticleResultVo saveToDraft(ArticleAddRo addRo) {
        return null;
    }

    @Override
    public void send(String media_id, String tag) {

    }

    @Override
    public void send(String media_id) {

    }

    @Override
    public void removeByLocalId(String localId) {

    }

    @Override
    public void updateByLocalId(String localId, ArticleAddRo updateRo) {

    }

    @Override
    public void removeArticleById(String localId) {

    }

    @Override
    public String view(String localId) {
        return null;
    }
}
