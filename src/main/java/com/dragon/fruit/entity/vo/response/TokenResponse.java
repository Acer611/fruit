package com.dragon.fruit.entity.vo.response;

/**
 * @program fruit
 * @description: token的返回结果
 * @author: Gaofei
 * @create: 2018/11/07 15:57
 */

public class TokenResponse extends  CommonResponse {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
