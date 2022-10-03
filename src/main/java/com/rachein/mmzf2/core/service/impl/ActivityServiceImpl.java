package com.rachein.mmzf2.core.service.impl;

import com.rachein.mmzf2.core.mapper.ActivityMapper;
import com.rachein.mmzf2.core.service.IActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.mmzf2.entity.DB.Activity;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动 服务实现类
 * </p>
 *
 * @author 吴远健
 * @since 2022-09-30
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

}
