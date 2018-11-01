package com.dragon.fruit.service.impl;

import com.dragon.fruit.dao.fruit.ChannelExposureDao;
import com.dragon.fruit.entity.po.fruit.ArticleInfoEntity;
import com.dragon.fruit.entity.po.fruit.ChannelExposureLogEntity;
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

        channelExposureDao.insertBatchTXArticle(channelExposureLogEntityList);

    }
}
