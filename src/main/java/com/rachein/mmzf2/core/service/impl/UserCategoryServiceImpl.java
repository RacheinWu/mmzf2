package com.rachein.mmzf2.core.service.impl;

import com.rachein.mmzf2.entity.new.DB.UserCategory;
import com.rachein.mmzf2.core.mapper.UserCategoryMapper;
import com.rachein.mmzf2.core.service.IUserCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户分类表：高中还是大学生还是社会人士 服务实现类
 * </p>
 *
 * @author 吴远健
 * @since 2022-09-30
 */
@Service
public class UserCategoryServiceImpl extends ServiceImpl<UserCategoryMapper, UserCategory> implements IUserCategoryService {

}
