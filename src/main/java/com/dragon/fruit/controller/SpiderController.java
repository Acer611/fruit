/*package com.dragon.fruit.controller;

import com.dragon.fruit.service.ISpiderJSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "爬虫接口")
@RestController
@RequestMapping("/spider")
public class SpiderController {
    private static final Logger logger = LoggerFactory.getLogger(SpiderController.class);


    @Autowired
    ISpiderJSService spiderJSService;

    @ApiOperation(value = "爬取急速系列数据")
    @ResponseBody
    @GetMapping("/getAccessToken")
    public void spiderJSvlue(){
        logger.info("爬取急速系列数据.......");
        spiderJSService.spiderJSvlue();


    }
}*/
