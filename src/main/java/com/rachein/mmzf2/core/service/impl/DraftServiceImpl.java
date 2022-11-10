package com.rachein.mmzf2.core.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.mmzf2.core.service.*;
import com.rachein.mmzf2.entity.DB.*;
import com.rachein.mmzf2.entity.DTO.vx.msg.ArticleReleaseByOpenidDTO;
import com.rachein.mmzf2.entity.DTO.vx.msg.ArticleVX;
import com.rachein.mmzf2.entity.RO.DraftApplicationRo;
import com.rachein.mmzf2.entity.RO.DraftCheckRo;
import com.rachein.mmzf2.entity.enums.DraftMethodEnum;
import com.rachein.mmzf2.entity.enums.DraftStateEnum;
import com.rachein.mmzf2.entity.enums.IdentityEnum;
import com.rachein.mmzf2.exception.GlobalException;
import com.rachein.mmzf2.redis.RedisService;
import com.rachein.mmzf2.redis.myPrefixKey.TableHeadPrefix;
import com.rachein.mmzf2.result.CodeMsg;
import com.rachein.mmzf2.utils.AccessTokenUtil;
import com.rachein.mmzf2.utils.HttpRequestUtils;
import com.rachein.mmzf2.utils.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/22
 * @Description
 */
@Service
@Transactional
@Slf4j
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

    @Autowired
    private ITableHeadVoService tableHeadVoService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IStudentHighInfoService studentHighInfoService;

    @Autowired
    private IStudentLow12InfoService studentLow12InfoService;

    @Autowired
    private IStudentLow3InfoService studentLow3InfoService;


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
                        .set(Draft::getMethod, DraftMethodEnum.ALL)
                        .set(Draft::getState, DraftStateEnum.PADDING)
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
                List<IdentityEnum> identity = ro.getTag().getIdentity();
                StringBuilder identity_str_db = new StringBuilder();
                for (IdentityEnum e : identity) {
                    identity_str_db.append(",").append(String.valueOf(e.getVal()));
                }
                method.setIdentity_list(identity_str_db.toString());
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
        //开始发送到微信服务器
        if (ro.getResult()) {
            //先放到草稿箱中：获取media_id
            String media_id = toDrawing(ro.getDraftId());
            if (StringUtils.isBlank(media_id)) {
                throw new GlobalException(CodeMsg.DRAFT_RELEASE_FAIL);
            }
            //从release_method表中找到对应的发送标签
            try {
                DraftReleaseMethod tag = methodService.lambdaQuery()
                        .eq(DraftReleaseMethod::getDraftId, ro.getDraftId())
                        .one();
                //从user表中找到对应的人群
                Set<String> toUsers = new HashSet<>();
                Arrays.stream(tag.getIdentity_list().split(",")).map(val -> {
                    return IdentityEnum.valueOf("I" + String.valueOf(val));
                }).collect(Collectors.toList())
                        .forEach(anEnum -> {
                            if (anEnum == IdentityEnum.I0) {
                                toUsers.addAll(studentLow12InfoService.lambdaQuery()
                                        .select(StudentLow12Info::getOpenid)
                                        .le(!Objects.isNull(tag.getAgeLower()), StudentLow12Info::getAge, tag.getAgeLower())
                                        .ge(!Objects.isNull(tag.getAgeUpper()), StudentLow12Info::getAge, tag.getAgeUpper())
                                        .list()
                                        .stream().map(StudentLow12Info::getOpenid)
                                        .collect(Collectors.toSet()));
                            }
                            else if (anEnum == IdentityEnum.I1) {
                                toUsers.addAll(studentLow3InfoService.lambdaQuery()
                                        .select(StudentLow3Info::getOpenid)
                                        .le(!Objects.isNull(tag.getAgeLower()), StudentLow3Info::getAge, tag.getAgeLower())
                                        .ge(!Objects.isNull(tag.getAgeUpper()), StudentLow3Info::getAge, tag.getAgeUpper())
                                        .list()
                                        .stream().map(StudentLow3Info::getOpenid)
                                        .collect(Collectors.toSet()));
                            }
                            else if (anEnum == IdentityEnum.I2) {
                                toUsers.addAll(studentHighInfoService.lambdaQuery()
                                        .select(StudentHighInfo::getOpenid)
                                        .eq(!Objects.isNull(tag.getMajor()), StudentHighInfo::getMajorName, tag.getMajor())
                                        .le(!Objects.isNull(tag.getAgeLower()), StudentHighInfo::getAge, tag.getAgeLower())
                                        .ge(!Objects.isNull(tag.getAgeUpper()), StudentHighInfo::getAge, tag.getAgeUpper())
                                        .list()
                                        .stream().map(StudentHighInfo::getOpenid)
                                        .collect(Collectors.toSet()));
                            }
                        });
                //调用微信服务器的api：
                ArticleReleaseByOpenidDTO sendByOid = new ArticleReleaseByOpenidDTO();
                Map<String, String> mpnews = new HashMap<>();
                //按照toUser进行发送：
                mpnews.put("media_id", media_id);
                sendByOid.setMpnews(mpnews);
                sendByOid.setTousers(toUsers);
                String url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=" + AccessTokenUtil.getToken();
                Response response = HttpRequestUtils.post(url, sendByOid);
                String response_json = response.body().string();
                log.info(response_json);
                if (!JSON.parseObject(response_json).get("errcode").equals(0)) {
                    throw new GlobalException(CodeMsg.DRAFT_RELEASE_FAIL);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new GlobalException(CodeMsg.DRAFT_RELEASE_FAIL);
            }
        }
    }

    @Override
    public Map<String, List> listApplication() {
        Map<String, List> map = new HashMap<>();
        //表头
        List<TableHead> head;
        head = redisService.getList(TableHeadPrefix.GET_DRAFT_HEAD, TableHeadPrefix.DRAFT_HEAD_PREFIX, TableHead.class);
        //如果redis中没有缓存，那么就从关系型数据库中查询
        if (Objects.isNull(head)) {
            head = tableHeadVoService.lambdaQuery()
                    .eq(TableHead::getBelong, "draft-review::list")
                    .list();
        }
        //数据
        List<Draft> body = draftService.lambdaQuery()
                .eq(Draft::getState, DraftStateEnum.PADDING)
                .list();
        //封装
        map.put("head", head);
        map.put("body", body);
        return map;
    }

    @Override
    public Map<String, Object> listTag(Long draftId) {
        DraftReleaseMethod method = methodService.lambdaQuery()
                .eq(DraftReleaseMethod::getDraftId, draftId)
                .one();
        Map<String, Integer> age = new HashMap<>();
        age.put("upper", method.getAgeUpper());
        age.put("lower", method.getAgeLower());
        IdentityEnum identity = method.getIdentity();
        String major = method.getMajor();
        Map<String, Object> map = new HashMap<>();
        map.put("age", age);
        map.put("identity", identity);
        map.put("major", major);
        return map;
    }

    @Override
    public List<Map<String, Object>> listDrawing() {
        //集装箱
        List<Map<String, Object>> ans = new ArrayList<>();
        //先获取状态为未发布的推文id
        List<Long> draftIds = draftService.lambdaQuery()
                .eq(Draft::getState, "未发布")
                .select(Draft::getId)
                .list()
                .stream().map(Draft::getId)
                .collect(Collectors.toList());
        //从文章表中获取对应头节点的子节点们
        draftIds.forEach(id -> {
            Map<String, Object> draft = new HashMap<>();
            List<Article> list = articleService.lambdaQuery()
                    .eq(Article::getDraftId, id)
                    .select(Article::getId, Article::getCoverPath, Article::getTitle)
                    .list();
            draft.put("draft_id", id);
            draft.put("articles", list);
            ans.add(draft);
        });
        return ans;
    }

    public String toDrawing(Long draftId) {
        //从数据库中查询
        List<ArticleVX> articleVXList = articleService.lambdaQuery()
                .eq(Article::getDraftId, draftId)
                .list()
                .stream().map(source -> {
                    ArticleVX target = new ArticleVX();
                    BeanUtils.copyProperties(source, target);
                    return target;
                })
                .collect(Collectors.toList());
        //
        Map<String, List<ArticleVX>> map = new HashMap<>();
        map.put("articles", articleVXList);
        String s = JSON.toJSONString(map);
        String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=" + AccessTokenUtil.getToken();
        try {
            Response response = HttpRequestUtils.post(url, s);
            String response_json = response.body().string();
            System.out.println(response_json);
            return JSON.parseObject(response_json).getString("media_id");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
