package com.dragon.fruit.entity.po.user;

import lombok.Data;

import java.util.Date;

/**
 * 用户登录日志  实体
 */
@Data
public class UserLoginLog {


    /** 主键 */
    private Long id;
    /** 用户ID */
    private Long userID;
    /** 用户昵称 */
    private String  userNickName;
    /** 登录时间 */
    private Date loginTime;
    /** 创建时间 */
    private Date createTime;
    /** IP地址 */
    private String ip;
    /** 设备号 */
    private String driveCode;
    /** 系统 */
    private String system;

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

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDriveCode() {
        return driveCode;
    }

    public void setDriveCode(String driveCode) {
        this.driveCode = driveCode;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}
