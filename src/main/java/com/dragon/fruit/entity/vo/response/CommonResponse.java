package com.dragon.fruit.entity.vo.response;

import com.dragon.fruit.common.constant.ErrorConstant;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @program slack
 * @description:
 * @author: apple
 * @create: 2018/10/31 09:36
 */

public class CommonResponse implements Serializable {

    /**
     * 错误返回码，正常时返回为0
     */
    @ApiModelProperty(value="错误返回码，正常时返回为0")
    private Integer retCode = ErrorConstant.SUCCESS;
    /**
     * 错误消息
     */
    @ApiModelProperty(value="错误消息")
    private String retMsg = "success";

    public CommonResponse() {
    }

    public CommonResponse(Integer retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public Integer getRetCode() {
        return retCode;
    }
    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }
    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}
