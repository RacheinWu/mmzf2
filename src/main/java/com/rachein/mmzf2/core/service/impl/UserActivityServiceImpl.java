package com.rachein.mmzf2.core.service.impl;

import com.rachein.mmzf2.entity.new.DB.UserActivity;
import com.rachein.mmzf2.core.mapper.UserActivityMapper;
import com.rachein.mmzf2.core.service.IUserActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户and活动报名关系 服务实现类
 * </p>
 *
 * @author 吴远健
 * @since 2022-09-30
 */
@Service
public class UserActivityServiceImpl extends ServiceImpl<UserActivityMapper, UserActivity> implements IUserActivityService {

}
