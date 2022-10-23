package com.rachein.mmzf2.core.service.impl;

import com.rachein.mmzf2.core.mapper.ViewAutoUserFillMapper;
import com.rachein.mmzf2.core.service.ViewAutoService;
import com.rachein.mmzf2.entity.VAO.UserFill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/23
 * @Description
 */
@Service
public class ViewAutoServiceImpl implements ViewAutoService {

    @Autowired
    private ViewAutoUserFillMapper userFillMapper;

    @Override
    public List<UserFill> listUserFill() {
        List<UserFill> result = userFillMapper.list();
        for (UserFill o : result) {
            o.setOptionValues(Arrays.asList(o.getOptionValuesStr().split(",")));
        }
        return result;
    }
}
