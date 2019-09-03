package com.dragon.fruit.service;

import com.dragon.fruit.entity.po.user.Order;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单业务层接口
 */
public interface IOrderService {

    /**
     * 生成订单接口
     * @param order
     * @return
     */
    Order saveOrder(Order order);

    /**
     * 回调方法 修改该订单信息
     * @param userID
     * @param orderID
     * @param money
     * @return
     */
    String updateOreder(Long userID, String orderID, BigDecimal money);

    /**
     * 根据userID 查询订单列表
     * @param userID
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Order> listOrder(Long userID, int pageNum, int pageSize);
}
