package com.dragon.fruit.dao.fruit;


import com.dragon.fruit.dao.fruit.sqlprovider.ChannelVisitSQLProvider;
import com.dragon.fruit.entity.po.fruit.UserChannelVisitLogEntity;
import org.apache.ibatis.annotations.*;

/**
 * @des 用户频道访问记录数据处理层
 * @author  Gaofei
 * @Date 2018-11-05
 */
@Mapper
public interface UserChannelVisitDao {
    /**
     * 获取频道访问记录
     * @param channelGuID
     * @param ip
     * @param userGuid
     * @return
     */
    @Select("SELECT ID,channelId,IP,userId,lastVisitTime,isDel as delFlag,visitCount,isRecommend FROM UserChannelVisitLog " +
            "WHERE channelId=#{channelGuID} and IP=#{ip} and userId=#{userGuid} ")
    UserChannelVisitLogEntity queryChannelVisitInfo(@Param("channelGuID") String channelGuID,
                                                    @Param("ip") String ip, @Param("userGuid") String userGuid);

    /**
     * 新增一条频道访问记录
     * @param userChannelVisitLogEntity
     */
    //@UpdateProvider(type= ChannelVisitSQLProvider.class, method="inesertUserChannelVisitLog")
    @Insert("insert into UserChannelVisitLog(ID,channelId,IP,userId,lastVisitTime,isDel,visitCount,isRecommend) " +
            " values(#{ID},#{channelId},#{IP},#{userId},#{lastVisitTime},#{delFlag},#{visitCount},#{isRecommend}" +
            ")" )
    void inesertUserChannelVisitLog(UserChannelVisitLogEntity userChannelVisitLogEntity);


    /**
     * 修改频道访问记录
     * @param userChannelVisitLogEntity
     */
    @UpdateProvider(type= ChannelVisitSQLProvider.class, method="updateUserChannelVisit")
    void updateUserChannelVisitLog(UserChannelVisitLogEntity userChannelVisitLogEntity);
}
