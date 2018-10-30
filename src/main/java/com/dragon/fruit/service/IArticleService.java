package com.dragon.fruit.service;


import com.dragon.fruit.entity.po.fruit.ArticleInfo;

import java.util.List;
import java.util.Map;

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
    public List<ArticleInfo> findArticeleByChannelID(String channelId, int pageNum, int pageSize);

    /**
     * 获取制定频道下最新的文章信息（默认取十条，若没有最新数据，随机推荐十条）
     * @param channelGuid
     * @param IP
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<ArticleInfo> findNewArticeleByChannelID(String channelGuid,String IP, int pageNum, int pageSize);

    /**
     * 根据文章的ID 查找文章的详细信息
     * @param titleId
     * @return
     */
    ArticleInfo findArticle(String titleId);
}
