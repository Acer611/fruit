package com.dragon.fruit.config;

import com.dragon.fruit.dao.fruit.TokenDao;
import com.dragon.fruit.dao.uuid.UUIDDao;
import com.dragon.fruit.entity.po.fruit.TokenEntity;
import com.dragon.fruit.entity.po.user.User;
import com.dragon.fruit.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *  定时任务操作类
 * @author  Gaofei
 * create by 2018-11-07
 *
 */
@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private TokenDao tokenDao;
    @Autowired
    private IUserService userService;
    @Autowired
    private UUIDDao uuidMapper;

    //@Scheduled(fixedRate = 60000) //上一次开始执行时间点之后5秒再执行
    //@Scheduled(fixedDelay = 5000) //上一次执行完毕时间点之后5秒再执行
    //@Scheduled(initialDelay=1000, fixedRate=5000) //第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
   // @Scheduled(cron="*/5 * * * * *")//通过cron表达式定义规则
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }

    /**
     * 定时删除过期Token的定时任务 （假删除）
     */
    //@Scheduled(fixedDelay = 60000) //上一次开始执行时间点之后1分钟后再执行
    public void removeExpiredToken(){
        logger.info("删除过期token的定时任务开始...");
        List<TokenEntity> tokenEntityList = tokenDao.queryExpiredToken(new Date());

        if(!tokenEntityList.isEmpty()&&tokenEntityList.size()>0){
            for (TokenEntity tokenEntity :tokenEntityList) {
                tokenEntity.setDelFlag(1);
                tokenEntity.setUpdateTime(new Date());
                tokenDao.updateToken(tokenEntity);
            }
        }

    }


    /**
     * 定时任务（每天凌晨1点 定时修改 过期会员的状态）
     */
    //@Scheduled(cron="*/5 * * * * *")//通过cron表达式定义规则
    @Scheduled(cron="0 0 1 1/1 * ? ") //每天凌晨1点执行
    public void updateExpiredUserStatus(){
        logger.info("定时任务修改过期用户的状态...");

        userService.updateExpiredUserStatus();

    }

}
