package com.dragon.fruit.entity.vo.response;

import com.dragon.fruit.entity.po.fruit.ArticleInfoEntity;
import com.dragon.fruit.entity.po.fruit.ChannelEntity;
import com.dragon.fruit.entity.po.fruit.UserInfoEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @program fruit
 * @description: 启动页返回对象 （首页返回对象）
 * @author: Gaofei
 * @create: 2018/10/31 09:30
 */

public class HomeResponse extends  CommonResponse{

    @ApiModelProperty(value = "用户信息")
    private UserInfoEntity userInfoEntity;

    @ApiModelProperty(value = "频道集合")
    private List<ChannelEntity> channelEntityList;

    @ApiModelProperty(value = "文章的集合")
    private List<ArticleInfoEntity> articleInfoEntityList;
    @ApiModelProperty(value = "当前频道GUID")
    private String currChannelGuid;

    public UserInfoEntity getUserInfoEntity() {
        return userInfoEntity;
    }

    public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
        this.userInfoEntity = userInfoEntity;
    }

    public List<ChannelEntity> getChannelEntityList() {
        return channelEntityList;
    }

    public void setChannelEntityList(List<ChannelEntity> channelEntityList) {
        this.channelEntityList = channelEntityList;
    }

    public List<ArticleInfoEntity> getArticleInfoEntityList() {
        return articleInfoEntityList;
    }

    public void setArticleInfoEntityList(List<ArticleInfoEntity> articleInfoEntityList) {
        this.articleInfoEntityList = articleInfoEntityList;
    }

    public String getCurrChannelGuid() {
        return currChannelGuid;
    }

    public void setCurrChannelGuid(String currChannelGuid) {
        this.currChannelGuid = currChannelGuid;
    }
}
