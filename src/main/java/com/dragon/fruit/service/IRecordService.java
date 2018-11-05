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

    /**
     *异步记录文章访问记录
     * @param ip IP地址
     * @param userGuid 用户的ID
     * @param articleInfoEntity 文章信息
     */
    void recordArticleVisitInfo(String ip, String userGuid, ArticleInfoEntity articleInfoEntity,String channelID);

    /**
     * 异步增加文章访问量
     * @param titleId
     */
    void updateVisitCountAsyn(String titleId,ArticleInfoEntity articleInfoEntity);

    /**
     * 异步记录频道访问记录
     * @param channelGuID 频道ID
     * @param userGuid 用户ID
     * @param ip IP
     */
    void recordChannelVist(String channelGuID, String userGuid, String ip);
}
