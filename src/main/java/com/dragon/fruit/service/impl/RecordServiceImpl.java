package com.dragon.fruit.service.impl;

import com.dragon.fruit.common.utils.UUIDUtil;
import com.dragon.fruit.dao.fruit.*;
import com.dragon.fruit.entity.po.fruit.*;
import com.dragon.fruit.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program fruit
 * @description: 异步记录行为的service
 * @author: Gaofei
 * @create: 2018/11/01 16:33
 */
@Service(value="recordService")
public class RecordServiceImpl  implements IRecordService {

    @Autowired
    ChannelExposureDao channelExposureDao;
    @Autowired
    UserArticleVisitDao userArticleVisitDao;
    @Autowired
    ArticleDao articleDao;
    @Autowired
    ChannelDao channelDao;
    @Autowired
    UserChannelVisitDao userChannelVisitDao;


    /**
     * 异步记录推荐文章记录到文章推荐表
     * @param channelGuID
     * @param IP
     * @param resultArticleList
     */
    @Async("taskExecutor")
    @Override
    public void recordTXArticle(String channelGuID, String IP, List<ArticleInfoEntity> resultArticleList) {
        List<ChannelExposureLogEntity> channelExposureLogEntityList = new ArrayList<>();

        for (ArticleInfoEntity articleInfo : resultArticleList) {
            ChannelExposureLogEntity channelExposureLogEntity = new ChannelExposureLogEntity();
            channelExposureLogEntity.setChannelGuid(channelGuID);
            channelExposureLogEntity.setIP(IP);
            channelExposureLogEntity.setCreateDate(new Date());
            channelExposureLogEntity.setTitleID(articleInfo.getTitleID());
            channelExposureLogEntityList.add(channelExposureLogEntity);
        }
        //  批量插入数据
        channelExposureDao.insertBatchTXArticle(channelExposureLogEntityList);

    }

    /**
     * 异步记录文章访问记录到文章访问记录表
     * @param ip IP地址
     * @param userGuid 用户的ID
     * @param articleInfoEntity 文章信息
     */
    @Async("taskExecutor")
    @Override
    public void recordArticleVisitInfo(String ip, String userGuid, ArticleInfoEntity articleInfoEntity,String channelID) {

        //查找用户是否访问过这篇文章
        UserArticleInfoVisitLogEntity  userVistInfo = userArticleVisitDao.findInfoByTitleId(articleInfoEntity.getTitleID(),ip,userGuid);

        UserArticleInfoVisitLogEntity userArticleInfoVisitLogEntity = new UserArticleInfoVisitLogEntity();
        userArticleInfoVisitLogEntity.setChannelID(channelID);

        userArticleInfoVisitLogEntity.setIP(ip);
        userArticleInfoVisitLogEntity.setIsDel(0);
        userArticleInfoVisitLogEntity.setTitle(articleInfoEntity.getTitle());
        userArticleInfoVisitLogEntity.setUserId(userGuid);
        userArticleInfoVisitLogEntity.setVisitTime(new Date());
        userArticleInfoVisitLogEntity.setTitleID(articleInfoEntity.getTitleID());
        //没有访问记录 新增一条数据
        if(userVistInfo==null){
            userArticleInfoVisitLogEntity.setID(UUIDUtil.getUuidStr());
            userArticleInfoVisitLogEntity.setVisitCount(1L);
            userArticleVisitDao.insertUserarticleVist(userArticleInfoVisitLogEntity);
        }//有访问记录访问次数+1
        else{
            userArticleInfoVisitLogEntity.setID(userVistInfo.getID());
            userArticleInfoVisitLogEntity.setVisitCount(userVistInfo.getVisitCount()+1);
            userArticleVisitDao.updateUserVistInfo(userArticleInfoVisitLogEntity);
        }


    }

    @Async("taskExecutor")
    @Override
    public void updateVisitCountAsyn(String titleId,ArticleInfoEntity articleInfoEntity) {
        Long visitCount = 1L;
        if(null!=articleInfoEntity.getVisitCount()){
            visitCount = articleInfoEntity.getVisitCount()+1;
            articleInfoEntity.setVisitCount(visitCount);
        }
        articleDao.updateVisitCount(articleInfoEntity);
    }

    @Async("taskExecutor")
    @Override
    public void recordChannelVist(String channelGuID, String userGuid, String ip) {

        //获取频道的信息 channel表
        ChannelEntity channelEntity = channelDao.queryChannelInfoByChannelGuid(channelGuID);
        int recommend = 1;
        if(null!=channelEntity.getRecommand()&&channelEntity.getRecommand()){
            recommend = 0;
        }

        //获取频道访问记录channelvistit表， 若没有记录新增记录， 若有记录访问次数加1
       UserChannelVisitLogEntity userChannelVisitLogEntity = userChannelVisitDao.queryChannelVisitInfo(channelGuID,ip,userGuid);
       if(null==userChannelVisitLogEntity){
           userChannelVisitLogEntity = new UserChannelVisitLogEntity();
           //新增一条数据
           userChannelVisitLogEntity.setID(UUIDUtil.getUuidStr());
           userChannelVisitLogEntity.setChannelId(channelGuID);
           userChannelVisitLogEntity.setIP(ip);
           userChannelVisitLogEntity.setIsRecommend(recommend);
           userChannelVisitLogEntity.setDelFlag(0);
           userChannelVisitLogEntity.setLastVisitTime(new Date());
           userChannelVisitLogEntity.setUserId(userGuid);
           userChannelVisitLogEntity.setVisitCount(1L);

           userChannelVisitDao.inesertUserChannelVisitLog(userChannelVisitLogEntity);
       }else{
           //修改数据信息访问次数+1,时间修改
           userChannelVisitLogEntity.setLastVisitTime(new Date());
           userChannelVisitLogEntity.setVisitCount(userChannelVisitLogEntity.getVisitCount()+1);
           userChannelVisitDao.updateUserChannelVisitLog(userChannelVisitLogEntity);
       }
       //Channel表visitCount + 1
        long visitCount = 1;
       if(null!=channelEntity.getVisitCount()){
           visitCount = channelEntity.getVisitCount()+visitCount;
       }
        channelEntity.setVisitCount(visitCount);
        System.out.println("频道访问次数为： "+ visitCount);
        channelDao.updateChannelVisitCount(channelEntity);

    }
}
