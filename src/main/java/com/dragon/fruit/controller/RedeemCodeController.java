package com.dragon.fruit.controller;

import com.dragon.fruit.service.IRedeemCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 兑换码的接口
 */
@Api(tags = "兑换码接口")
@Controller
@RequestMapping("/api/redeemCode")
public class RedeemCodeController {
    @Autowired
    private IRedeemCodeService redeemCodeService;

    /**
     * 生成兑换码
     *
     * @return
     */
    @ApiOperation(value = "生成兑换码")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Map generateRedeemCode(@RequestHeader(required = false, value = "token") String token,
                                  @ApiParam(value = "生成兑换码数量", required = true) @RequestParam(name = "number") Integer number,
                                  @ApiParam(value = "生成兑换码类型    1 --> 10天 ,2 ---> 15 天  3 ---> 30 天", required = true) @RequestParam(name = "type") Integer type) {

        Map<String, Object> map = new HashMap<>();
        if (number < 0) {
            map.put("code", 501);
            map.put("message", "生成数量输入有误！");
            return map;
        }

        if (type < 1 || type > 3) {
            map.put("code", 502);
            map.put("message", "类型输入有误！");
            return map;
        }
        String result = redeemCodeService.generateRedeemCode(number, type);
        if (result.equalsIgnoreCase("ok")) {
            map.put("code", 200);
            map.put("message", "success");

        } else {
            map.put("code", 500);
            map.put("message", "服务器错误请联系管理员");
        }

        return map;
    }


    /**
     * 使用兑换码
     *
     * @return
     */
    @ApiOperation(value = "使用兑换码")
    @ResponseBody
    @RequestMapping(value = "/used", method = RequestMethod.POST)
    public Map used(@RequestHeader(required = false, value = "token") String token,
                    @ApiParam(value = "用户ID", required = true) @RequestParam(name = "userID") Long userID,
                    @ApiParam(value = "兑换码", required = true) @RequestParam(name = "code") String code) {

        Map<String, Object> map = redeemCodeService.userRedeemCode(userID, code);

        return map;
    }


}
