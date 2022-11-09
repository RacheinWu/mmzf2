package com.rachein.mmzf2.core.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.mmzf2.core.service.IArticleService;
import com.rachein.mmzf2.core.service.IDraftReleaseMethodService;
import com.rachein.mmzf2.core.service.IDraftService;
import com.rachein.mmzf2.entity.DB.Draft;
import com.rachein.mmzf2.entity.DB.DraftReleaseMethod;
import com.rachein.mmzf2.entity.DTO.tag.TagDTO;
import com.rachein.mmzf2.entity.RO.DraftApplicationRo;
import com.rachein.mmzf2.entity.enums.DraftMethodEnum;
import com.rachein.mmzf2.entity.enums.DraftStateEnum;
import com.rachein.mmzf2.exception.GlobalException;
import com.rachein.mmzf2.result.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/22
 * @Description
 */
@Service
@Transactional
public class DraftServiceImpl extends ServiceImpl<BaseMapper<Draft>, Draft> implements IDraftService {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private IDraftReleaseMethodService methodService;

    @Autowired
    private IDraftService draftService;

    @Override
    public void application(DraftApplicationRo ro) {
        //从数据库里面找到对应的article
        if (!draftService.lambdaQuery().eq(Draft::getId, ro.getDraft_id()).exists()) {
            throw new GlobalException(CodeMsg.DRAFT_NOT_FOUND);
        }
        try {
            //拆箱判断：
            if (ro.getMethod() == DraftMethodEnum.ALL) {
                //群发：
                draftService.lambdaUpdate().eq(Draft::getId, ro.getDraft_id()).set(Draft::getState, DraftMethodEnum.ALL).update();//更新
            }
            else {
                //按标签发：
                draftService.lambdaUpdate().eq(Draft::getId, ro.getDraft_id()).set(Draft::getState, DraftMethodEnum.TAG).update();//更新
                //在数据库里面保存
                DraftReleaseMethod method = new DraftReleaseMethod();
                method.setDraftId(ro.getDraft_id());
                method.setAgeLower(ro.getTag().getAge().getLower());
                method.setAgeUpper(ro.getTag().getAge().getUpper());
                method.setMajor(ro.getTag().getMajor());
                method.setIdentity(ro.getTag().getIdentity());
                methodService.save(method);//保存
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(CodeMsg.DRAFT_APPLICATION_FAIL);
        }

    }
}
