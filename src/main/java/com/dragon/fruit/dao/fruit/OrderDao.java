package com.dragon.fruit.dao.fruit;

import com.dragon.fruit.entity.po.user.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/***
 * 订单dao曾
 */
@Mapper
public interface OrderDao {

    /**
     * 保存订单信息
     * @param order
     */
    @Insert(" insert into`order`(orderID,userID,pcID,money,createTime,`status`) " +
            " values(#{orderID},#{userID},#{pcID},#{money},#{createTime},#{status})")
    public void saveOrder(Order order) ;

    /**
     * 修改订单状态 根据订单编号
     * @param orderID
     */
    @Update("update `order` set `status` = 1 where  orderID = #{orderID}")
    void updateOrderStatus(String orderID);

    /**
     * 根据订单编号查询订单信息
     * @param orderID
     * @return
     */
    @Select("select * from `order` where orderID =#{orderID}")
    Order findOrderByID(String orderID);

    /**
     * 查询当前用户下的订单列表
     * @param userID
     * @return
     */
    @Select("select * from `order` where userID =#{userID} limit 10")
    List<Order> listOrderByUserID(Long userID);

    /**
     * 根据用户ID 查询订单
     * @param userID
     * @return
     */
    @Select("select o.*,p.`name`,p.time as days from `order` o LEFT JOIN  priceconfig p on o.pcID =p.id  where o.userID =#{userID}")
    List<Order> listOrder(Long userID);
}
