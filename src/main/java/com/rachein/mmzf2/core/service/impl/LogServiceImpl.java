package com.rachein.mmzf2.core.service.impl;

import com.rachein.mmzf2.entity.DB.Log;
import com.rachein.mmzf2.core.mapper.LogMapper;
import com.rachein.mmzf2.core.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志 服务实现类
 * </p>
 *
 * @author 吴远健
 * @since 2022-09-30
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
