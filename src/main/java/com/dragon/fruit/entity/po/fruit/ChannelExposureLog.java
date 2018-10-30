package com.dragon.fruit.entity.po.fruit;

import java.sql.Date;

/**
 * 推荐记录表
 * @author  Gaofei
 * @date 2018-10-30
 */
public class ChannelExposureLog {

	private Integer id;
	private String channelGuid;
	private String titleID;
	private Date createDate;
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
