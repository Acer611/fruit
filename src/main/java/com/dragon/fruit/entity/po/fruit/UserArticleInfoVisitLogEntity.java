package com.dragon.fruit.entity.po.fruit;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @program fruit
 * @description: 用户文章访问记录实体
 * @author: Gaofei
 * @create: 2018/11/02 14:12
 */

public class UserArticleInfoVisitLogEntity implements Serializable {

    @ApiModelProperty(value = "唯一标识")
    private String ID;
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "IP")
    private String IP;
    @ApiModelProperty(value = "文章标题")
    private String titleID;
    @ApiModelProperty(value = "单人访问次数")
    private Long visitCount;
    @ApiModelProperty(value = "是否删除")
    private Integer isDel;
    @ApiModelProperty(value = "文章标题")
    private String title;
    @ApiModelProperty(value = "频道ID")
    private String channelID;
    @ApiModelProperty(value = "最后访问时间")
    private Date visitTime;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getTitleID() {
        return titleID;
    }

    public void setTitleID(String titleID) {
        this.titleID = titleID;
    }

    public Long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Long visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }
}
