package com.dragon.fruit.service.impl;

import cn.jsms.api.SendSMSResult;
import com.dragon.fruit.common.utils.UUIDUtil;
import com.dragon.fruit.dao.fruit.CheckCodeDao;
import com.dragon.fruit.entity.po.user.CheckCode;
import com.dragon.fruit.service.ICheckCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 检验验证码的实现类
 */

@Service(value="checkCodeService")
public class CheckCodeServiceImpl implements ICheckCodeService{

    @Autowired
    CheckCodeDao checkCodeMapper;

    @Override
    public void saveCheckCode(String phone, String ip, SendSMSResult result) {
        //先检查当前手机号是否已经存在若存在更新megID 否则插入新数据
        CheckCode checkCodeEntity = checkCodeMapper.findCheckCodeBYPhone(phone);
        if(checkCodeEntity!=null){
            checkCodeMapper.deleteCheckCodeByPhone(phone);
        }
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime expireAt = dateTime.plusMinutes(5);
        CheckCode checkCode = new CheckCode();
        checkCode.setId(UUIDUtil.getUuidStr().replace("-",""));
        checkCode.setCreateAt(dateTime);
        checkCode.setPhone(phone);
        checkCode.setIp(ip);
        checkCode.setExpireAt(expireAt);
        checkCode.setMsgId(result.getMessageId());
        checkCode.setIsUse(0);
        checkCode.setDelFlag(0);
        checkCodeMapper.save(checkCode);
    }

    @Override
    public CheckCode findCheckCodeBYPhone(String phone) {
        return checkCodeMapper.findCheckCodeBYPhone(phone);
    }
}
