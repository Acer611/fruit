package com.dragon.fruit.entity.po.fruit;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @program fruit
 * @description: 用户频道访问记录实体
 * @author: Gaofei
 * @create: 2018/11/05 09:33
 */

public class UserChannelVisitLogEntity implements Serializable {


    @ApiModelProperty(value = "唯一标示")
    private String ID;
    @ApiModelProperty(value = "频道ID")
    private String channelId;
    @ApiModelProperty(value = "IP")
    private String IP;
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "访问次数")
    private Long visitCount;
    @ApiModelProperty(value = "最后访问时间")
    private Date lastVisitTime;
    @ApiModelProperty(value = "是否是推荐频道 0 是  1 否")
    private Integer isRecommend;
    @ApiModelProperty(value = "删除标识 0 未删除 1 已删除")
    private Integer delFlag;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Long visitCount) {
        this.visitCount = visitCount;
    }

    public Date getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(Date lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
