package com.rachein.mmzf2.core.service.impl;

import com.rachein.mmzf2.core.mapper.ViewAutoUserFillMapper;
import com.rachein.mmzf2.core.service.ViewAutoService;
import com.rachein.mmzf2.entity.VAO.UserFill;
import org.apache.commons.lang.StringUtils;
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
    public List<UserFill> listUserFill(Integer id) {
        List<UserFill> result = userFillMapper.list(id);
        for (UserFill o : result) {
            String optionValuesStr = o.getOptionValuesStr();
            if (!StringUtils.isBlank(optionValuesStr)) {
                o.setOptionValues(Arrays.asList(optionValuesStr.split("/")));
            }
        }
        return result;
    }
}
