package com.dragon.fruit.entity.vo.response;

import com.dragon.fruit.entity.po.fruit.ChannelEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @program fruit
 * @description: 频道返回结果
 * @author: Gaofei
 * @create: 2018/11/15 14:24
 */

public class ChannelResponse extends CommonResponse{

    @ApiModelProperty(value = "频道的集合")
    private List<ChannelEntity> channelEntityList;

    public List<ChannelEntity> getChannelEntityList() {
        return channelEntityList;
    }

    public void setChannelEntityList(List<ChannelEntity> channelEntityList) {
        this.channelEntityList = channelEntityList;
    }
}
