package com.dragon.fruit.entity.po.fruit;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 推荐记录表
 * @author  Gaofei
 * @date 2018-10-30
 */
public class ChannelExposureLogEntity {

    @ApiModelProperty(value = "唯一标识")
	private Integer id;
    @ApiModelProperty(value = "频道的ID")
	private String channelGuid;
    @ApiModelProperty(value = "文章的ID")
	private String titleID;
    @ApiModelProperty(value = "创建时间")
	private Date createDate;
    @ApiModelProperty(value = "IP地址")
	private String IP;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelGuid() {
        return channelGuid;
    }

    public void setChannelGuid(String channelGuid) {
        this.channelGuid = channelGuid;
    }

    public String getTitleID() {
        return titleID;
    }

    public void setTitleID(String titleID) {
        this.titleID = titleID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
}
