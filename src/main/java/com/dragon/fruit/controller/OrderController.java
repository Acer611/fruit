package com.dragon.fruit.controller;

import com.dragon.fruit.entity.po.user.Order;
import com.dragon.fruit.service.IOrderService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "订单操作接口")
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @ApiOperation(value = "保存订单")
    @ResponseBody
    @PostMapping("/saveOrder")
    public Map saveOrder(@RequestHeader(required = false, value = "token") String token,
                         @RequestBody Order order) {
        Map resultMap = new HashMap();
        Order orderInfo = orderService.saveOrder(order);

        if (null != orderInfo) {
            resultMap.put("code", 200);
            resultMap.put("message", "success");
        } else {
            resultMap.put("code", 500);
            resultMap.put("message", "error");
        }
        return resultMap;
    }


    @ApiOperation(value = "支付回调方法调用")
    @ResponseBody
    @PostMapping("/updateOreder")
    public Map updateOreder(Long userID, String orderID, BigDecimal money) {
        Map resultMap = new HashMap();
        String result = orderService.updateOreder(userID, orderID, money);

        if (result.equalsIgnoreCase("ok")) {
            resultMap.put("code", 200);
            resultMap.put("message", "success");
        } else {
            resultMap.put("code", 500);
            resultMap.put("message", result);
        }
        return resultMap;
    }


    @ApiOperation(value = "查询订单列表")
    @ResponseBody
    @GetMapping("/listOrder")
    public Map listOrder(@RequestHeader(required = false, value = "token") String token,
                         @RequestParam(name = "userID") Long userID,
                         @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        Map resultMap = new HashMap();
        PageInfo<Order> orderInfoList = orderService.listOrder(userID, pageNum, pageSize);

        resultMap.put("code", 200);
        resultMap.put("message", "success");
        resultMap.put("data", orderInfoList);
        return resultMap;
    }


}
