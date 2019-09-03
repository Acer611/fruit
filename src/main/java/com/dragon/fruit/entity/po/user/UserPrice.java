package com.dragon.fruit.entity.po.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户购买套餐关联关系
 */
@Data
public class UserPrice {

    @ApiModelProperty(value = "唯一标示")
    private Long id;
    @ApiModelProperty(value = "用户的ID")
    private Long userID;
    @ApiModelProperty(value = "套餐的ID")
    private Long pcId;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "过期时间")
    private Date expriedDate;
    @ApiModelProperty(value = "是否删除   0  未删除  1 已删除")
    private Integer status ;

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

    public Long getPcId() {
        return pcId;
    }

    public void setPcId(Long pcId) {
        this.pcId = pcId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpriedDate() {
        return expriedDate;
    }

    public void setExpriedDate(Date expriedDate) {
        this.expriedDate = expriedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
