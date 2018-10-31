package com.dragon.fruit.service;


import com.dragon.fruit.entity.po.fruit.ArticleInfoEntity;
import com.dragon.fruit.entity.vo.response.ArticleFVResponse;

import java.util.List;

/**
 * @des  文章业务逻辑处理层
 * @Date 2018-10-30
 */
public interface IArticleService {
    /**
     * 查询频道下的文章信息
     * @param channelId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<ArticleInfoEntity> findArticeleByChannelID(String channelId, int pageNum, int pageSize);

    /**
     * 获取制定频道下最新的文章信息（默认取十条，若没有最新数据，随机推荐十条）
     * @param channelGuid
     * @param IP
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<ArticleInfoEntity> findNewArticeleByChannelID(String channelGuid, String IP, int pageNum, int pageSize);

    /**
     * 根据文章的ID 查找文章的详细信息
     * @param titleId
     * @return
     */
    ArticleInfoEntity findArticle(String titleId);


    /***
     * 获取首页信息
     * @param userGuid 用户的ID
     * @return
     */
    ArticleFVResponse getHomeInfo(String userGuid);
}
