package com.dragon.fruit.dao.uuid;

import com.dragon.fruit.entity.po.user.UserLoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户登录数据操作层
 */
@Mapper
public interface UserLoginLogDao {

    /**
     * 保存用户登录日志
     * @param userLoginLog
     */
    @Insert(" insert into user_loginlog(userID,userNickName,loginTime,createTime) " +
            " values(#{userID},#{userNickName},#{loginTime},#{createTime})")
    void saveUserLoginLog(UserLoginLog userLoginLog);
}
