package com.dragon.fruit.service;

import java.util.Map;

/**
 * 生成兑换码的service接口
 */
public interface IRedeemCodeService {


    /**
     * 生成兑换码
     *
     * @param type
     * @param number
     * @return
     */
    String generateRedeemCode( Integer number,Integer type);

    /**
     * 使用兑换码
     * @param userID
     * @param code
     * @return
     */
    Map<String,Object> userRedeemCode(Long userID, String code);
}
