package com.dragon.fruit.dao.fruit.sqlprovider;

import com.dragon.fruit.entity.po.fruit.TokenEntity;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * @program fruit
 * @description: Toke sql
 * @author: Gaofei
 * @create: 2018/11/07 14:46
 */

public class TokenSQLProvider {


    /**
     * 修改token信息
     * @param tokenEntity
     * @return
     */

    public String updateToken(TokenEntity tokenEntity){

        return new SQL() {{
            {
                UPDATE("Token");
                if(!StringUtils.isEmpty(tokenEntity.getToken())){
                    SET("token=#{token}");
                }
                if(!StringUtils.isEmpty(tokenEntity.getUpdateTime())){
                    SET("updateTime=#{updateTime}");
                }
                if(!StringUtils.isEmpty(tokenEntity.getDelFlag())){
                    SET("isDel=#{delFlag}");
                }
                if(!StringUtils.isEmpty(tokenEntity.getExpiredTime())){
                    SET("expiredTime=#{expiredTime}");
                }

                WHERE("ID=#{ID}");
            }
        }}.toString();
    }





}
