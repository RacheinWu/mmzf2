package com.rachein.mmzf2.core.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.mmzf2.core.service.IDraftArticleService;
import com.rachein.mmzf2.core.service.IDraftService;
import com.rachein.mmzf2.entity.DB.Draft;
import com.rachein.mmzf2.entity.DB.DraftArticleRelation;
import org.springframework.stereotype.Service;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/22
 * @Description
 */
@Service
public class DraftServiceImpl extends ServiceImpl<BaseMapper<Draft>, Draft> implements IDraftService {
    @Override
    public boolean save(Draft draft) {
        try {
            save(draft);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
