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
}
