package com.dragon.fruit.service;

import com.dragon.fruit.entity.po.user.User;

import java.util.Map;

/**
 * 用户信息service层接口
 */
public interface IUserService {

    /**
     * 保存用户注册信息
     * @param user
     * @return
     */
    String saveRegisterUser(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    Map<String,Object> login(User user);

    /**
     * 生成用户的邀请码
     * @param userID
     * @return
     */
    Map<String,Object> generateUserAskCode(Long userID);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    Map<String,Object> updateUser(User user);

    /**
     * 获取用户基本信息
     * @param userID
     * @return
     */
    Map<String,Object> userDetail(Long userID);

    /**
     * 定时任务定时修改过期用户的状态
     */
    void updateExpiredUserStatus();

    /**
     * 用户登出
     * @param id
     * @return
     */
    Map<String,Object> loginOut(String id);
}
