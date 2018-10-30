package com.dragon.fruit.entity.vo.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @description: 用户阅读记录实体返回对象
 * @author: Gaofei
 * @create: 2018/10/18 13:47
 */

public class UserReadHistoryResponse implements Serializable {


    //用户名
    @ApiModelProperty(value = "用户名")
    private String userName;
    //杂志名
    @ApiModelProperty(value = "杂志名")
    private String magazineName;
    //文章名称
    @ApiModelProperty(value = "文章名称")
    private String articleName;
    //文章所属年份
    @ApiModelProperty(value = "文章所属年份")
    private String year;
    //文章属于哪一期
    @ApiModelProperty(value = "文章属于哪一期")
    private String issue;
    //阅读次数
    @ApiModelProperty(value = "阅读次数")
    private Long readCount;
    //阅读时间
    @ApiModelProperty(value = "阅读时间")
    private String date;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMagazineName() {
        return magazineName;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
    }


    public Long getReadCount() {
        return readCount;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
