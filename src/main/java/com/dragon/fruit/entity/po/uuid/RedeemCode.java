package com.dragon.fruit.entity.po.uuid;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 兑换码实体
 */
@Data
public class RedeemCode {

    @ApiModelProperty(value = "唯一标示")
    private Long id;
    @ApiModelProperty(value = "'兑换码'")
    private String code;
    @ApiModelProperty(value = "过期时间")
    private Date expireDate;
    @ApiModelProperty(value = "是否被使用 0 未使用 1 已使用")
    private Integer isUsed;
    @ApiModelProperty(value = "类型  1   10天  2  15 天  3  30 天")
    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
