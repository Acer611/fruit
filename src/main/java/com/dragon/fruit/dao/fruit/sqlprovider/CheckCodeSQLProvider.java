package com.dragon.fruit.dao.fruit.sqlprovider;

import com.dragon.fruit.entity.po.fruit.ChannelEntity;
import com.dragon.fruit.entity.po.user.CheckCode;
import com.dragon.fruit.entity.po.user.User;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class CheckCodeSQLProvider {


    //update check_code set is_use =#{isUse},use_at=#{useAt} WHERE phone =#{phone}
    public String updateCheckCode (CheckCode checkCode){
        return new SQL() {{
            {
                UPDATE("check_code");
                if(!StringUtils.isEmpty(checkCode.getIsUse())){
                    SET("is_use=#{isUse}");
                }
                if(!StringUtils.isEmpty(checkCode.getUseAt())){
                    SET("use_at=#{useAt}");
                }

                WHERE("phone=#{phone}");
            }
        }}.toString();
    }


    /**
     * 生成邀请码
     * @param user
     * @return
     */
    public String generateAskCode (User user){
        return new SQL() {{
            {
                UPDATE("user");
                if(!StringUtils.isEmpty(user.getAskCode())){
                    SET("askCode=#{askCode}");
                }
                WHERE("id=#{id}");
            }
        }}.toString();
    }


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public String updateUser(User user){

        return new SQL() {{
            {
                UPDATE("user");
                if(!StringUtils.isEmpty(user.getExpiredDate())){
                    SET("expiredDate=#{expiredDate}");
                }
                if(!StringUtils.isEmpty(user.getNickName())){
                    SET("nickName=#{nickName}");
                }
                if(!StringUtils.isEmpty(user.getPassword())){
                    SET("password=#{password}");
                }
                if(!StringUtils.isEmpty(user.getIsVip())){
                    SET("isVip=#{isVip}");
                }

                WHERE("id=#{id}");
            }
        }}.toString();
    }
}
