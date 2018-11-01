package com.dragon.fruit.service;

import com.dragon.fruit.entity.po.fruit.ArticleInfoEntity;

import java.util.List;

/**
 * @program fruit
 * @description: 异步 记录行为的service
 * @author: Gaofei
 * @create: 2018/11/01 16:29
 */

public interface IRecordService {

    /**
     * 记录推荐文章记录到文章推荐表
     * @param channelGuID 频道ID
     * @param IP  IP
     * @param resultArticleList 文章列表
     */
    void recordTXArticle(String channelGuID, String IP, List<ArticleInfoEntity> resultArticleList);
}
