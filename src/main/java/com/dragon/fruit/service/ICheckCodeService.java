package com.dragon.fruit.service;


import cn.jsms.api.SendSMSResult;
import com.dragon.fruit.entity.po.user.CheckCode;

/**
 * 检验验证码信息
 */
public interface ICheckCodeService {

    /**
     *  保存验证码信息
     */
    void saveCheckCode(String phone, String ip, SendSMSResult result);

    /**
     * 根据手机号查询手机验证码信息
     * @param phone
     * @return
     */
    CheckCode findCheckCodeBYPhone(String phone);
}
