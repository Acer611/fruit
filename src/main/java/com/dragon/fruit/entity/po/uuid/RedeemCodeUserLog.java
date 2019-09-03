package com.dragon.fruit.entity.po.uuid;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 兑换码使用记录实体
 */
@Data
public class RedeemCodeUserLog {

    @ApiModelProperty(value = "唯一标示")
    private Long id;
    @ApiModelProperty(value = "用户的ID")
    private Long userID;
    @ApiModelProperty(value = "兑换码")
    private String redeemCode;
    @ApiModelProperty(value = "兑换码使用时间")
    private Date useTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getRedeemCode() {
        return redeemCode;
    }

    public void setRedeemCode(String redeemCode) {
        this.redeemCode = redeemCode;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }
}
