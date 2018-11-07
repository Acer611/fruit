package com.dragon.fruit.entity.po.fruit;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @program fruit
 * @description: 签名验证实体类
 * @author: apple
 * @create: 2018/11/07 14:02
 */

public class TokenEntity  implements Serializable {

    @ApiModelProperty(value = "唯一标示")
    private String ID;
    @ApiModelProperty(value = "加密的签名值")
    private String token;
    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expiredTime;
    @ApiModelProperty(value = "是否删除标识")
    private Integer delFlag;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
