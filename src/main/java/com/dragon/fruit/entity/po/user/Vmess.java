package com.dragon.fruit.entity.po.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 线路实体类
 */
@Data
public class Vmess implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "'线路名称'")
    private String name;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "端口")
    private String port;
    @ApiModelProperty(value = "额外ID")
    private Integer security;
    @ApiModelProperty(value = "加密方式 0 none 1 auto 2 aes-128-cfb 3 aes-128-gcm 4 chacha20-poly1305")
    private Integer alterId;
    @ApiModelProperty(value = "传输协议 1 tcp 2 kcp 3 ws 4 h2 ")
    private Integer network;
    @ApiModelProperty(value = "别名 默认 default")
    private String remarks;
    @ApiModelProperty(value = "伪装类型 默认none")
    private String type;
    @ApiModelProperty(value = "伪装域名")
    private String host;
    @ApiModelProperty(value = "路径 默认 /help")
    private String path;
    @ApiModelProperty(value = "底层传输安全 默认tls")
    private String hsts;
    @ApiModelProperty(value = " 分配安全 0  false 1 true 默认true")
    private Integer allowInsecure;
    @ApiModelProperty(value = "是否是vip线路 0 不是  1 是")
    private Integer isVip;
    @ApiModelProperty(value = "UUID")
    private String UUID;
    @ApiModelProperty(value = "是否选中 0 否  1 是")
    private Integer isCheck;
    @ApiModelProperty(value = "当前用户ID")
    private Long userID;
    @ApiModelProperty(value = "国家")
    private String country;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "版本号")
    private String configVersion;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Integer getSecurity() {
        return security;
    }

    public void setSecurity(Integer security) {
        this.security = security;
    }

    public Integer getAlterId() {
        return alterId;
    }

    public void setAlterId(Integer alterId) {
        this.alterId = alterId;
    }

    public Integer getNetwork() {
        return network;
    }

    public void setNetwork(Integer network) {
        this.network = network;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHsts() {
        return hsts;
    }

    public void setHsts(String hsts) {
        this.hsts = hsts;
    }

    public Integer getAllowInsecure() {
        return allowInsecure;
    }

    public void setAllowInsecure(Integer allowInsecure) {
        this.allowInsecure = allowInsecure;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getConfigVersion() {
        return configVersion;
    }

    public void setConfigVersion(String configVersion) {
        this.configVersion = configVersion;
    }
}
