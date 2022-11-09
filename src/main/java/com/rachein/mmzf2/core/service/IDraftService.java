package com.rachein.mmzf2.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rachein.mmzf2.entity.DB.Draft;
import com.rachein.mmzf2.entity.RO.DraftApplicationRo;
import com.rachein.mmzf2.entity.RO.DraftCheckRo;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/21
 * @Description
 */
public interface IDraftService extends IService<Draft> {

    void application(DraftApplicationRo ro);

    void check(DraftCheckRo ro);

}
