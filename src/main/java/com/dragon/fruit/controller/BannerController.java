package com.dragon.fruit.controller;

import com.dragon.fruit.service.IBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "Banner接口")
@RestController
@RequestMapping("/api/banner")
public class BannerController {

    @Autowired
    private IBannerService bannerService;

    /**
     * 查询banner列表
     * @return
     */
    @ApiOperation(value = "查询banner列表")
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Map listBanner(@RequestHeader(required = false, value = "token") String token){

        Map<String,Object> map = bannerService.listBanner();

        return map;
    }
}
