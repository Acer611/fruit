package com.dragon.fruit.dao.fruit;


import com.dragon.fruit.entity.po.fruit.Channel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 频道数据处理层
 * @author  Gaofei
 * @date 2018-10-25
 */
@Mapper
public interface ChannelDao {
    /**
     * 获取channel信息
     * @param channelGuid
     * @return
     */
    @Select("SELECT * FROM Channel WHERE ChannelGuid=#{channelGuid}")
    Channel queryChannelInfoByChannelGuid(String channelGuid);
}
