package com.dragon.fruit.controller;

import com.dragon.fruit.entity.po.user.User;
import com.dragon.fruit.entity.po.user.Vmess;
import com.dragon.fruit.service.IVmessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "线路操作接口")
@RestController
@RequestMapping("/api/vmess")
public class VmessController {

    @Autowired
    private IVmessService vmessService;

    /**
     * 查询当前用户下的可用下路列表
     *
     * @return
     */
    @ApiOperation(value = "查询当前用户下的可用下路列表")
    @ResponseBody
    @RequestMapping(value = "/listVmess", method = RequestMethod.GET)
    public Map listVmess(@RequestHeader(required = false, value = "token") String token,
                         @RequestParam(name = "userID") Long userID) {

        Map<String, Object> map = vmessService.listVmess(userID);

        return map;
    }

    /*    *//**
     * 保存用户当前选择的线路
     * @return
     *//*
    @ApiOperation(value = "保存用户当前选择的线路")
    @ResponseBody
    @RequestMapping(value = "/saveUserVmess",method = RequestMethod.POST)
    public Map saveUserVmess(*//*@RequestParam(name = "userID")Long userID,@RequestParam(name = "vmessID")Long vmessID*//*
    @RequestBody Vmess vmess){

        Map<String,Object> map = vmessService.saveUserVmess(vmess.getUserID(),vmess.getId());

        return map;
    }*/
}
