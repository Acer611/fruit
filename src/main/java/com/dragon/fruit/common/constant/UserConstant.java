package com.dragon.fruit.common.constant;

/**
 * @program Fruit
 * @description: 用户常量类
 * @author: Gaofei
 * @create: 2018/10/31 11:17
 */

public class UserConstant {

    //默认用户的ID
    public static  final  String  DEFAULT_USER="2d68bf7a-5d89-4a3c-be98-aee9eb6fdf74";


    //每个注册用户Redis key的前缀
    public static final String REGISTER_PREFIX="register_";
    //用户每天发送验证码个数的前缀
    public static final String REGISTER_COUNT_PREFIX="register_count_";
    //允许发送短信验证码的最大次数
    public static final Integer MESSAGE_MAX_SEND_TIMES = 5;

    //用户信息的key
    public final static String USER_INFOES_ALL="user:";

    //TODO 邀请码链接 待修改
    public final static String ASK_URL="www.baidu.com?askCode=";


}
