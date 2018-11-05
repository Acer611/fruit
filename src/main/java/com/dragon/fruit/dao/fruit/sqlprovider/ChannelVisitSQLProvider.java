package com.dragon.fruit.dao.fruit.sqlprovider;

import com.dragon.fruit.entity.po.fruit.UserChannelVisitLogEntity;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * @program fruit
 * @description:
 * @author: apple
 * @create: 2018/11/05 11:25
 */

public class ChannelVisitSQLProvider {


    /**
     * 修改频道访问表中的访问次数
     * @param userChannelVisitLogEntity
     * @return
     */

    public String updateUserChannelVisit(UserChannelVisitLogEntity userChannelVisitLogEntity){

        return new SQL() {{
            {
                UPDATE("UserChannelVisitLog");
                if(!StringUtils.isEmpty(userChannelVisitLogEntity.getVisitCount())){
                    SET("visitCount=#{visitCount}");
                }
                if(!StringUtils.isEmpty(userChannelVisitLogEntity.getLastVisitTime())){
                    SET("lastVisitTime=#{lastVisitTime}");
                }

                WHERE("ID=#{ID}");
            }
        }}.toString();
    }



}
