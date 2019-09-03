/*
package com.dragon.fruit.controller;


import com.dragon.fruit.common.constant.ErrorConstant;
import com.dragon.fruit.entity.vo.response.TokenResponse;
import com.dragon.fruit.service.ITokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

*/
/**
 * @program Fruit
 * @description: Toke的接口
 * @author: Gaofei
 * @create: 2018/11/07 14:58
 *//*

@Api(tags = "果实频接口获取Token接口")
@RestController
@RequestMapping("/tools/token")
public class TokenController {

    private static final Logger logger = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    ITokenService tokenService;

    */
/**
     *获取AccessToken token有效期两小时，过期请重新重新获取
     * @return
     *//*


    @ApiOperation(value = "获取AccessToken")
    @ResponseBody
    @GetMapping("/getAccessToken")
    public TokenResponse getAccessToken(){
        logger.info("生成token.......");
        TokenResponse tokenResponse = new TokenResponse();
        String token = tokenService.getAccessToken();
        if(null== token){
            logger.info("生成token出错，没有token值。。。。。。");
            tokenResponse.setRetCode(ErrorConstant.TOKEN_CODE);
            tokenResponse.setRetMsg(ErrorConstant.TOKEN_MESSAGE);
            return  tokenResponse;
        }
        tokenResponse.setToken(token);
        return tokenResponse;
    }


}
*/
