package com.dragon.fruit.entity.po.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 套餐价格实体
 */
@Data
public class PriceConfig implements Serializable {
    @ApiModelProperty(value = "唯一标示")
    private Long id;
    @ApiModelProperty(value = "套餐名称")
    private String name;
    @ApiModelProperty(value = "时长")
    private Integer time;
    @ApiModelProperty(value = "套餐类型 1 一个月  2 三个月  3 半年  4 一年")
    private Integer type;
    @ApiModelProperty(value = "价格")
    private BigDecimal price;
    @ApiModelProperty(value = "是否启用 0 未启用  1 启用")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
