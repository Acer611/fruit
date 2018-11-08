package com.dragon.fruit.service.impl;

import com.dragon.fruit.common.constant.Constant;
import com.dragon.fruit.common.utils.UUIDUtil;
import com.dragon.fruit.dao.fruit.TokenDao;
import com.dragon.fruit.entity.po.fruit.TokenEntity;
import com.dragon.fruit.service.ITokenService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @program fruit
 * @description:
 * @author: Gaofei
 * @create: 2018/11/07 15:16
 */

@Service(value = "tokenService")
public class TokenServiceImple  implements ITokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImple.class);

    @Autowired
    TokenDao tokenDao;

    /**
     * 生成加密签名   MD5(加密固定KEY + UUID + 当前时间 )生成
     * @return
     */
    @Override
    public String getAccessToken() {
        //生成TOKEN
        String sign = null;
        String key = Constant.KEY;
        String uuid = UUIDUtil.getUuidStr();
        sign = key + uuid+ new Date().getTime();
        String token = DigestUtils.md5Hex(sign);

        logger.info("......token : " + token);
        LocalDateTime expiredTime =  LocalDateTime.now();
        expiredTime = expiredTime.plusHours(2);

        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setID(uuid);
        tokenEntity.setToken(token);
        tokenEntity.setExpiredTime(expiredTime);
        tokenEntity.setCreateTime(new Date());
        tokenEntity.setUpdateTime(new Date());
        tokenEntity.setDelFlag(0);

        tokenDao.insertToken(tokenEntity);

        return token;
    }
}
