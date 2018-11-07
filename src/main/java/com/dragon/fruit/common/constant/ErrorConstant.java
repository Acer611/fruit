package com.dragon.fruit.common.constant;

/**
 * @program fruit
 * @description: 定义返回返回错误码 和message 的 常量类
 * @author: Gaofei
 * @create: 2018/10/31 09:39
 */

public class ErrorConstant {

    /**
     *
     */
    public static final Integer SUCCESS  = 0;

    public static final String ERROR_MESSAGE = "";


    /**
     * Token 生成出错
     */
    public static final Integer TOKEN_CODE  = 4001;
    public static final String TOKEN_MESSAGE = "TOKEN 服务出错";

    /**
     * 用户没有APP
     */
    public static final Integer NOAPPCODE  = 5000;
    public static final String NOAPPCODE_MESSAGE = "当前用户没有APP,请到后台系统配置好APP和Channel";

    /**
     * Channel
     */
    public static final Integer NOCHANNEL_CODE  = 5001;
    public static final String NOCHANNEL_MESSAGE = "当前用户的APP没有配置频道,请到后台系统配置好Channel";


}
