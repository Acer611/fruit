package com.dragon.fruit.dao.fruit.sqlprovider;

import com.dragon.fruit.entity.po.fruit.ChannelEntity;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * @program fruit
 * @description: 频道SQL
 * @author: Gaofei
 * @create: 2018/11/05 14:11
 */

public class ChannelSQLProvider {

    /**
     * 修改呢频道表的访问次数
     * @param channelEntity
     * @return
     */
    public String updateChannelVisitCount(ChannelEntity channelEntity){

        return new SQL() {{
            {
                UPDATE("Channel");
                if(!StringUtils.isEmpty(channelEntity.getVisitCount())){
                    SET("VisitCount=#{visitCount}");
                }
                if(!StringUtils.isEmpty(channelEntity.getUpdateDate())){
                    SET("UpdateDate=#{updateDate}");
                }

                WHERE("ChannelGuid=#{channelGuid}");
            }
        }}.toString();
    }

}
