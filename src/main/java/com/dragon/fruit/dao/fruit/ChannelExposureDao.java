package com.dragon.fruit.dao.fruit;


import com.dragon.fruit.entity.po.fruit.ChannelExposureLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 推荐记录数据层
 */

@Mapper
public interface ChannelExposureDao {
    /**
     * 根据频道ID和IP查找当前频道最新推荐的10条数据
     * @param channelGuid
     * @param IP
     * @return
     */
    @Select("SELECT top 10 ID,ChannelGuid,TitleID,CreateDate,IP  FROM ChannelExposureLog WHERE ChannelGuid= #{channelGuid} AND IP='0:0:0:0:0:0:0:1' ORDER BY CreateDate DESC")
    List<ChannelExposureLogEntity> findChannelExposureByChangIDAndIP(@Param("channelGuid")String channelGuid, @Param("IP")String IP);

    /**
     * 批量插入推荐文章到文章推荐记录表
     * @param channelExposureLogEntityList
     */
    void insertBatchTXArticle(List<ChannelExposureLogEntity> channelExposureLogEntityList);
}
