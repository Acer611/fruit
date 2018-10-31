package com.dragon.fruit.dao.fruit;


import com.dragon.fruit.entity.po.fruit.ChannelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 频道数据处理层
 * @author  Gaofei
 * @date 2018-10-25
 */
@Mapper
public interface ChannelDao {
    /**
     * 获取channel信息根据频道ID
     * @param channelGuid
     * @return
     */
    @Select("SELECT * FROM Channel WHERE ChannelGuid=#{channelGuid}")
    ChannelEntity queryChannelInfoByChannelGuid(String channelGuid);

    /**
     * 根据APPGuid 获取频道信息列表
     * @param appGuid
     * @return
     */
    @Select("SELECT * FROM Channel WHERE AppId=#{appGuid} ORDER BY ChannelSort ")
    List<ChannelEntity> queryChannelByAppID(String appGuid);
}
