package com.dragon.fruit.service.impl;

import com.dragon.fruit.common.utils.DateUtils;
import com.dragon.fruit.common.utils.UUIDUtil;
import com.dragon.fruit.dao.fruit.UserDao;
import com.dragon.fruit.dao.uuid.IRedeemCodeDao;
import com.dragon.fruit.entity.po.user.User;
import com.dragon.fruit.entity.po.uuid.RedeemCode;
import com.dragon.fruit.entity.po.uuid.RedeemCodeUserLog;
import com.dragon.fruit.service.IRedeemCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 兑换码业务层接口
 */

@Service(value="redeemCodeService")
public class RedeemCodeServiceImpl implements IRedeemCodeService {

    @Autowired
    private IRedeemCodeDao redeemCodeMapper;

    @Autowired
    private UserDao userMapper;

    /**
     * 生成兑换码
     *
     * @param type
     * @param number
     * @return
     */
    @Override
    public String generateRedeemCode( Integer number,Integer type) {

        for (int i = 0; i <number ; i++) {
            RedeemCode redeemCode = new RedeemCode();
            String uuid = UUIDUtil.getUuidStr().replace("-","").toUpperCase();
            LocalDateTime expiredDate = DateUtils.dateToLocalDateTime(new Date());
            LocalDateTime newExpiredDate = expiredDate.plusDays(60);
            Date expiredTime = DateUtils.localDateTimeToDate(newExpiredDate);
            redeemCode.setCode(uuid);
            redeemCode.setExpireDate(expiredTime);
            redeemCode.setIsUsed(0);
            redeemCode.setType(type);
            redeemCodeMapper.save(redeemCode);
        }

        return "ok";
    }


    /**
     * 使用兑换码
     * @param userID
     * @param code
     * @return
     */
    @Override
    public Map<String, Object> userRedeemCode(Long userID, String code) {

        Map map = new HashMap();
        User user = userMapper.findUserByID(userID);
        if(null==user){
            map.put("code",501);
            map.put("message","用户信息输入有误！");
            return map;
        }

        RedeemCode redeemCode = redeemCodeMapper.findByCode(code);
        if(redeemCode.getExpireDate().before(new Date())){
            map.put("code",502);
            map.put("message","兑换码已过期！");
            return map;
        }
        if(redeemCode.getIsUsed()==1){
            map.put("code",503);
            map.put("message","兑换码已被使用！");
            return map;
        }

        // 修改用户的过期时间
        modifyUserExpireDate(user,redeemCode);

        // 修改验证码状态
        redeemCodeMapper.updateRedeemCodeStatusByCode(code);
        map.put("code",200);
        map.put("message","success");


        return map;
    }

    /**
     * 增加用户兑换码相应时长
     * @param user
     * @param redeemCode
     */
    private void modifyUserExpireDate(User user, RedeemCode redeemCode) {

        //修改用户时长
        Date userExpireDate = user.getExpiredDate();
        LocalDateTime expiredDate = DateUtils.dateToLocalDateTime(userExpireDate);
        switch(redeemCode.getType()){
            case 1:
                expiredDate = expiredDate.plusDays(10);
                break;
            case 2:
                expiredDate = expiredDate.plusDays(15);
                break;
            case 3:
                expiredDate = expiredDate.plusDays(30);
                break;

            default:
                expiredDate = expiredDate.plusDays(10);
                break;
        }
        Date newExpiredDate = DateUtils.localDateTimeToDate(expiredDate);
        user.setExpiredDate(newExpiredDate);
        userMapper.updateUser(user);

        //记录兑换码使用日志
        RedeemCodeUserLog redeemCodeUserLog = new RedeemCodeUserLog();
        redeemCodeUserLog.setUserID(user.getId());
        redeemCodeUserLog.setRedeemCode(redeemCode.getCode());
        redeemCodeUserLog.setUseTime(new Date());
        redeemCodeMapper.saveRedeemCodeUserLog(redeemCodeUserLog);


    }
}
