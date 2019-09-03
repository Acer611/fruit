package com.dragon.fruit.entity.po.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单实体表
 */
@Data
public class Order {

    @ApiModelProperty(value = "唯一标示")
    private Long id;
    @ApiModelProperty(value = "订单ID")
    private String orderID;
    @ApiModelProperty(value = "用户ID")
    private Long userID;
    @ApiModelProperty(value = "套餐ID")
    private Long pcID;
    @ApiModelProperty(value = "支付金额")
    private BigDecimal money;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "订单状态 0 支付中  1 支付完成")
    private Integer status;
    @ApiModelProperty(value = "套餐名称")
    private  String name;
    @ApiModelProperty(value = "套餐天数")
    private  Integer days;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getPcID() {
        return pcID;
    }

    public void setPcID(Long pcID) {
        this.pcID = pcID;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
