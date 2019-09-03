package com.dragon.fruit.service.impl;

import com.dragon.fruit.common.utils.DateUtils;
import com.dragon.fruit.common.utils.UUIDUtil;
import com.dragon.fruit.dao.fruit.PriceDao;
import com.dragon.fruit.dao.fruit.OrderDao;
import com.dragon.fruit.dao.fruit.UserDao;
import com.dragon.fruit.dao.fruit.VmessDao;
import com.dragon.fruit.dao.uuid.UUIDDao;
import com.dragon.fruit.entity.po.user.*;
import com.dragon.fruit.entity.po.uuid.UUID;
import com.dragon.fruit.entity.po.uuid.UserUUID;
import com.dragon.fruit.service.IOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service(value="orderService")
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderDao orderMapper;
    @Autowired
    private UserDao userMapper;
    @Autowired
    private PriceDao priceMapper;

    @Autowired
    private UUIDDao uuidMapper;

    @Autowired
    private VmessDao vmessMappr;


    /**
     * 生成订单信息
     * @param order
     * @return
     */
    @Override
    public Order saveOrder(Order order) {

       /* String orderId = UUIDUtil.getOrderIDWithTime();
        order.setOrderID(orderId);*/
        order.setCreateTime(new Date());
        order.setStatus(0);
        orderMapper.saveOrder(order);

        return order;
    }

    /**
     * 回调方法
     * @param userID
     * @param orderID
     * @param money
     * @return
     */
    @Override
    public String updateOreder(Long userID, String orderID, BigDecimal money) {
        // 修改订单状态
        orderMapper.updateOrderStatus(orderID);

        // 修改当前用户vip 状态和 会员到期时间
        Order order = orderMapper.findOrderByID(orderID);
        User user = userMapper.findUserByID(userID);
        user.setIsVip(order.getPcID());
        Date expireDate = user.getExpiredDate();
        if(expireDate == null){
            expireDate = new Date();
        }
        //查看套餐明细
        PriceConfig priceConfig = priceMapper.detail(order.getPcID());
        LocalDateTime expiredDate8 = DateUtils.dateToLocalDateTime(expireDate);

        //套餐类型 1 一个月  2 三个月  3 半年  4 一年
        LocalDateTime newExpiredDate8 = expiredDate8;
        switch(priceConfig.getType()){
            case 1:
                newExpiredDate8 = expiredDate8.plusMonths(1);
                break;
            case 2:
                newExpiredDate8 = expiredDate8.plusMonths(3);
                break;
            case 3:
                newExpiredDate8 = expiredDate8.plusMonths(6);
                break;
            case 4:
                newExpiredDate8 = expiredDate8.plusYears(1);
                break;
            default:
                newExpiredDate8 = expiredDate8.plusMonths(1);
                break;
        }
        Date newExpiredDate = DateUtils.localDateTimeToDate(newExpiredDate8);
        user.setExpiredDate(newExpiredDate);
        userMapper.updateUser(user);
        //给用户分配vip 线路 的uuid
        inserVIPVmess(user);


        //查询当前用户下的订单信息
        List<Order> orderList = orderMapper.listOrderByUserID(userID);

        if(orderList.size()==1){
            // 修改邀请者的时长
            User fatherUser = userMapper.findUserByID(user.getPid());
            if(null!=fatherUser){
                Date pexpireDate = fatherUser.getExpiredDate();
                if(pexpireDate == null){
                    pexpireDate = new Date();
                }
                LocalDateTime pexpiredDate8 = DateUtils.dateToLocalDateTime(pexpireDate);
                LocalDateTime pnewExpiredDate8 = pexpiredDate8.plusDays(5);
                Date pnewExpiredDate = DateUtils.localDateTimeToDate(pnewExpiredDate8);
                fatherUser.setExpiredDate(pnewExpiredDate);
                // 修改邀请人的时长
                userMapper.updateUser(fatherUser);

            }

        }



        return "ok";
    }

    /**
     * 给付费用户分配VIP 线路 和 UUID
     * @param user
     */
    private void inserVIPVmess(User user) {

        // 分配线路
        //获取要分配的VIP线路
        Vmess vemess = vmessMappr.findVIPVmess();
        /*UserVmess userVmess = new UserVmess();
        userVmess.setUserID(user.getId());
        userVmess.setVmessID(vemess.getId());
        userVmess.setCreateDate(new Date());
        vmessMappr.saveUserVmess(userVmess);*/

        //修改当前线路已分配人数
        vmessMappr.updateUserPeople(vemess.getId());


        //分配UUID
        UserUUID userUUID = new UserUUID();
        // 查找当前使用次数最少的VIP的UUID
        UUID uuid = uuidMapper.findVIPUUID(vemess.getId());
        userUUID.setUserID(user.getId());
        userUUID.setUuid(uuid.getUuid());
        userUUID.setIsVip(1);
        userUUID.setUid(uuid.getId());
        userUUID.setCreateDate(new Date());
        userUUID.setVmessID(vemess.getId());
        // 保存UUID和用户的关联关系
        uuidMapper.saveUserUUID(userUUID);

        // 修改UUID 的使用次数
        uuidMapper.updateUUIDTimes(uuid.getId());
    }

    /**
     * 根据用户ID 查询用户下订单列表
     * @param userID
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Order> listOrder(Long userID, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.listOrder(userID);
        PageInfo result = new PageInfo(orderList);
        return result;
    }
}
