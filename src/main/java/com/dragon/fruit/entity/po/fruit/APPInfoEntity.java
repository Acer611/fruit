package com.dragon.fruit.entity.po.fruit;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @program Fruit
 * @description: App信息实体类
 * @author: Gaofei
 * @create: 2018/10/31 11:35
 */

public class APPInfoEntity  implements Serializable {

    @ApiModelProperty(value = "用户唯一标识")
    private  Long Id;
    @ApiModelProperty(value = "APP ID")
    private String appGuid;
    @ApiModelProperty(value = "APP名称")
    private String appName;
    @ApiModelProperty(value = "用户ID")
    private String userID;
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    @ApiModelProperty(value = "修改时间")
    private Date updateDate;
    @ApiModelProperty(value = "创建人")
    private String createUser;
    @ApiModelProperty(value = "修改人")
    private String updateUser;
    @ApiModelProperty(value = "密匙")
    private String secret;
    @ApiModelProperty(value = "图片logo")
    private String img;
    @ApiModelProperty(value = "更新规则")
    private Integer updateRule;
    @ApiModelProperty(value = "是否启用")
    private Integer isEnable;
    @ApiModelProperty(value = "xx频道")
    private String secChannel;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAppGuid() {
        return appGuid;
    }

    public void setAppGuid(String appGuid) {
        this.appGuid = appGuid;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getUpdateRule() {
        return updateRule;
    }

    public void setUpdateRule(Integer updateRule) {
        this.updateRule = updateRule;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getSecChannel() {
        return secChannel;
    }

    public void setSecChannel(String secChannel) {
        this.secChannel = secChannel;
    }
}
