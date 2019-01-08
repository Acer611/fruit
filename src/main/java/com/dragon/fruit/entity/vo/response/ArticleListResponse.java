package com.dragon.fruit.entity.vo.response;

import com.dragon.fruit.entity.po.fruit.ArticleInfoEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @program fruit
 * @description: 文章列表返回对象
 * @author: Gaofei
 * @create: 2018/11/01 10:12
 */

public class ArticleListResponse  extends  CommonResponse{

    @ApiModelProperty(value = "文章的集合")
    private List<ArticleInfoEntity> articleInfoEntityList ;

    @ApiModelProperty(value = "总条数")
    private Long total;

    @ApiModelProperty(value = "是否是最后一页")
    private Boolean isLastPage;

    @ApiModelProperty(value = "当前频道GUID")
    private String currChannelGuid;

    public List<ArticleInfoEntity> getArticleInfoEntityList() {
        return articleInfoEntityList;
    }

    public void setArticleInfoEntityList(List<ArticleInfoEntity> articleInfoEntityList) {
        this.articleInfoEntityList = articleInfoEntityList;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Boolean getLastPage() {
        return isLastPage;
    }

    public void setLastPage(Boolean lastPage) {
        isLastPage = lastPage;
    }

    public String getCurrChannelGuid() {
        return currChannelGuid;
    }

    public void setCurrChannelGuid(String currChannelGuid) {
        this.currChannelGuid = currChannelGuid;
    }
}
