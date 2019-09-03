package com.dragon.fruit.entity.po.user;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 */
@Data
public class User implements Serializable {

    /** 主键 */
    @ApiModelProperty(value = "唯一标示")
    @JSONField(name = "id")
    private Long id;
    /** 手机号 */
    @ApiModelProperty(value = "手机号")
    @JSONField(name = "phone")
    private String phone;
    /** 密码 */
    @ApiModelProperty(value = "密码")
    @JSONField(name = "password")
    private String password;
    /** 昵称 */
    @ApiModelProperty(value = "昵称")
    @JSONField(name = "nickName")
    private String nickName;
    /** 头像 */
    @ApiModelProperty(value = "头像")
    @JSONField(name = "id")
    private String imageUrl;
    /** 设备号 */
    @ApiModelProperty(value = "设备号")
    @JSONField(name = "driveCode")
    private String driveCode;
     /** 用户类型 0 普通用户  1 vip用户 */
    @ApiModelProperty(value = "用户类型 0 普通用户   关联套餐的ID 默认 0 免费用户")
    @JSONField(name = "isVip")
    private Long isVip;
    /** 过期时间 */
    @ApiModelProperty(value = "过期时间")
    @JSONField(name = "expiredDate")
    private Date expiredDate;
    /** 状态  0  正常  1 删除 */
    @ApiModelProperty(value = "状态  0  正常  1 删除")
    @JSONField(name = "status")
    private Integer status;

    @ApiModelProperty(value = "手机验证码")
    @JSONField(name = "checkCod")
    private String checkCod;

    @ApiModelProperty(value = "邀请码")
    @JSONField(name = "askCode")
    private String askCode;

    @ApiModelProperty(value = "父ID")
    @JSONField(name = "pid")
    private Long pid;

    @ApiModelProperty(value = "创建时间")
    @JSONField(name = "createTime")
    private Date createTime;

    @ApiModelProperty(value = "套餐ID")
    @JSONField(name = "tcID")
    private Long tcID;
    @ApiModelProperty(value = "套餐名称")
    @JSONField(name = "tcName")
    private String tcName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDriveCode() {
        return driveCode;
    }

    public void setDriveCode(String driveCode) {
        this.driveCode = driveCode;
    }

    public Long getIsVip() {
        return isVip;
    }

    public void setIsVip(Long isVip) {
        this.isVip = isVip;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCheckCod() {
        return checkCod;
    }

    public void setCheckCod(String checkCod) {
        this.checkCod = checkCod;
    }

    public String getAskCode() {
        return askCode;
    }

    public void setAskCode(String askCode) {
        this.askCode = askCode;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getTcID() {
        return tcID;
    }

    public void setTcID(Long tcID) {
        this.tcID = tcID;
    }

    public String getTcName() {
        return tcName;
    }

    public void setTcName(String tcName) {
        this.tcName = tcName;
    }
}
