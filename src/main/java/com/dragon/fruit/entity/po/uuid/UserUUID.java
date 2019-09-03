package com.dragon.fruit.entity.po.uuid;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户和UUID关联关系表
 */
@Data
public class UserUUID {

    @ApiModelProperty(value = "唯一标示")
    private Long id;
    @ApiModelProperty(value = "UUID")
    private String uuid;
    @ApiModelProperty(value = "用户ID")
    private Long userID;
    @ApiModelProperty(value = "0 不是  1  是")
    private Integer isVip;
    @ApiModelProperty(value = "UUID表的ID")
    private Long uid;
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    @ApiModelProperty(value = "线路ID")
    private Long vmessID;
    //是否删除  0 否 1 是
    private Integer delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getVmessID() {
        return vmessID;
    }

    public void setVmessID(Long vmessID) {
        this.vmessID = vmessID;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
