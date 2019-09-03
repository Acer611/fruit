package com.dragon.fruit.controller;


import com.dragon.fruit.entity.po.user.User;
import com.dragon.fruit.service.IPriceConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "套餐配置操作接口")
@RestController
@RequestMapping("/api/priceConfig")
public class PriceConfigController {

    @Autowired
    private IPriceConfig priceConfig;


    @ApiOperation(value = "查询所有套餐")
    @ResponseBody
    @GetMapping("/list")
    public Map list(@RequestHeader(required = false, value = "token") String token) {
        Map resultMap = new HashMap();
        resultMap = priceConfig.list();
        return resultMap;
    }

    @ApiOperation(value = "查询套餐详情")
    @ResponseBody
    @GetMapping("/detail")
    public Map detail(@RequestHeader(required = false, value = "token") String token,
                      @RequestParam(name = "id") Long id) {
        Map resultMap = new HashMap();
        resultMap = priceConfig.detail(id);
        return resultMap;
    }
}
