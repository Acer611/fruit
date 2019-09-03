package com.dragon.fruit.entity.po.uuid;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Banner {
    @ApiModelProperty(value = "唯一标示")
     private Long id;
     @ApiModelProperty(value = "banner")
     private  String banner;
     @ApiModelProperty(value = "跳转地址")
     private String tagertUrl;
     @ApiModelProperty(value = "状态")
     private Integer status;


    public Banner() {

    }

    public Banner(Banner baner) {
        this.banner = baner.getBanner()==null?"":baner.getBanner();
        this.tagertUrl = baner.getTagertUrl()==null?"":baner.getTagertUrl();
        this.id = baner.getId();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getTagertUrl() {
        return tagertUrl;
    }

    public void setTagertUrl(String tagertUrl) {
        this.tagertUrl = tagertUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
