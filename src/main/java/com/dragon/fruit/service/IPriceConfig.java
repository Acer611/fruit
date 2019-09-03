package com.dragon.fruit.service;

import java.util.Map;

/**
 * 套餐信息业务层
 */
public interface IPriceConfig {

    /**
     * 获取现有套餐列表
     * @return
     */
    Map list();

    /**
     * 根据套餐ID查询套餐详情
     * @param id
     * @return
     */
    Map detail(Long id);
}
