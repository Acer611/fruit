package com.dragon.fruit.entity.po.uuid;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * uuID
 */

@Data
public class UUID {

    @ApiModelProperty(value = "唯一标示")
    private Long id;
    @ApiModelProperty(value = "UUID")
    private String uuid;
    @ApiModelProperty(value = "节点，线路ID  关联 vmess表的ID")
    private Long vmessID;
    @ApiModelProperty(value = "是否使用 0 未使用 1 使用")
    private Integer isEnable;

    @ApiModelProperty(value = "是否VIP 0 不是 1 是")
    private Integer isVIP;

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

    public Long getVmessID() {
        return vmessID;
    }

    public void setVmessID(Long vmessID) {
        this.vmessID = vmessID;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getIsVIP() {
        return isVIP;
    }

    public void setIsVIP(Integer isVIP) {
        this.isVIP = isVIP;
    }
}
