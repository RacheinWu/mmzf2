package com.rachein.mmzf2.core.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.mmzf2.core.service.IArticleService;
import com.rachein.mmzf2.core.service.IDraftReleaseMethodService;
import com.rachein.mmzf2.core.service.IDraftService;
import com.rachein.mmzf2.entity.DB.Draft;
import com.rachein.mmzf2.entity.DB.DraftReleaseMethod;
import com.rachein.mmzf2.entity.RO.DraftApplicationRo;
import com.rachein.mmzf2.entity.RO.DraftCheckRo;
import com.rachein.mmzf2.entity.enums.DraftMethodEnum;
import com.rachein.mmzf2.entity.enums.DraftStateEnum;
import com.rachein.mmzf2.exception.GlobalException;
import com.rachein.mmzf2.result.CodeMsg;
import com.rachein.mmzf2.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${wechat.msg.application}")
    private String application_msg_template_id;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private IDraftReleaseMethodService methodService;

    @Autowired
    private IDraftService draftService;

    @Autowired
    private VXServiceImpl vxService;

    @Override
    public void application(DraftApplicationRo ro) {
        //获取当前登录用户的信息:

        //从数据库里面找到对应的article
        if (!draftService.lambdaQuery().eq(Draft::getId, ro.getDraft_id()).exists()) {
            throw new GlobalException(CodeMsg.DRAFT_NOT_FOUND);
        }
        try {
            //拆箱判断：
            if (ro.getMethod() == DraftMethodEnum.ALL) {
                //群发：
                draftService.lambdaUpdate().eq(Draft::getId, ro.getDraft_id())
                        .set(Draft::getState, DraftMethodEnum.ALL)
                        .set(Draft::getApplicantId, "")
                        .set(Draft::getApplicant, "")
                        .update();//更新
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
            //微信公众号发送消息到指定的账号：
            String openId = StpUtil.getLoginIdAsString();
            String current_user_openId = StpUtil.getLoginIdAsString();
            MessageUtil.applicationResult("审核已提交", "等待一级管理员审核", "无", current_user_openId, application_msg_template_id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(CodeMsg.DRAFT_APPLICATION_FAIL);
        }
    }


    @Override
    public void check(DraftCheckRo ro) {
        //获取审核人员：
//        StpUtil.getSession()
        lambdaUpdate().eq(Draft::getId, ro.getDraftId())
                .set(Draft::getState, ro.getResult()?DraftStateEnum.RELEASED : DraftStateEnum.DRAWING)
                .update();
        //找到申请的人
        Draft db = lambdaQuery().eq(Draft::getId, ro.getDraftId()).select(Draft::getApplicantId).one();
        //发微信公众号消息：
        MessageUtil.applicationResult("推文审核", (ro.getResult()?"成功":"驳回"), ro.getRemark(), db.getApplicantId(), application_msg_template_id);
    }
}
