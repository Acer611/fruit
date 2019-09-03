package com.dragon.fruit.entity.po.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 短信验证码实体
 *
 */

@Data
public class CheckCode implements Serializable {

    //'唯一标识'
    private String id;
     //'手机号'
     @ApiModelProperty(value = "手机号")
    private String phone;
    //'ip 地址'
    private String ip;
    //'验证码'
    @ApiModelProperty(value = "验证码")
    private String checkCode;
    //'创建时间'
    private LocalDateTime createAt;
    //'过期时间'
    private LocalDateTime expireAt;
    //'是否使用 0 未使用  1 已使用'
    private Integer isUse;
    //'使用时间'
    private LocalDateTime useAt;
    //'是否删除 0 未删除  1 已删除'
    private Integer delFlag;
    private String msgId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public LocalDateTime getUseAt() {
        return useAt;
    }

    public void setUseAt(LocalDateTime useAt) {
        this.useAt = useAt;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
