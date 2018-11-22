package com.dragon.fruit.service;


import com.dragon.fruit.entity.po.fruit.ArticleInfoEntity;
import com.dragon.fruit.entity.vo.response.ArticleListResponse;
import com.dragon.fruit.entity.vo.response.ChannelResponse;
import com.dragon.fruit.entity.vo.response.HomeResponse;

import java.util.Date;

/**
 * @des  文章业务逻辑处理层
 * @Date 2018-10-30
 */
public interface IArticleService {

    /***
     * 获取首页信息
     * @param userGuid 用户的ID
     * @return
     */
    HomeResponse getHomeInfo(String userGuid, String IP);
    /**
     * 查询频道下的文章信息
     * @param channelId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ArticleListResponse findArticeleByChannelID(String channelId, Date createTime, String IP, String userGuid,int pageNum, int pageSize);

    /**
     * 获取制定频道下最新的文章信息（默认取十条，若没有最新数据，随机推荐十条）
     * @param channelGuid
     * @param IP
     * @param pageNum
     * @param pageSize
     * @return
     */
    ArticleListResponse findNewArticeleByChannelID(String channelGuid, String IP, String userGuid,int pageNum, int pageSize);

    /**
     * 根据文章的ID 查找文章的详细信息
     * @param titleId
     * @param IP
     * @return
     */
    ArticleInfoEntity findArticle(String titleId,String IP,String userGuid,String channelID);


    /**
     * 获取频道列表
     * @param userGuid
     * @return
     */
    ChannelResponse queryChannelList(String userGuid);
}
