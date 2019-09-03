package com.dragon.fruit.entity.po.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserVmess {

    @ApiModelProperty(value = "唯一标示")
    private Long id;
    @ApiModelProperty(value = "线路ID")
    private Long vmessID;
    @ApiModelProperty(value = "用户ID")
    private Long userID;
    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVmessID() {
        return vmessID;
    }

    public void setVmessID(Long vmessID) {
        this.vmessID = vmessID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
