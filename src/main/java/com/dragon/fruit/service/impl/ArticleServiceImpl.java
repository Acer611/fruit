package com.dragon.fruit.service.impl;

import com.dragon.fruit.common.constant.ErrorConstant;
import com.dragon.fruit.common.constant.UserConstant;
import com.dragon.fruit.common.utils.ListRandomUtils;
import com.dragon.fruit.dao.fruit.*;
import com.dragon.fruit.entity.po.fruit.*;
import com.dragon.fruit.entity.vo.response.ArticleListResponse;
import com.dragon.fruit.entity.vo.response.HomeResponse;
import com.dragon.fruit.service.IArticleService;
import com.dragon.fruit.service.IRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @program fruit
 * @description: 文章业务逻辑处理层
 * @author: Gaofei
 * @create: 2018/10/30 16:08
 */
@Service(value="articleService")
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    ArticleDao articleDao;
    @Autowired
    ChannelExposureDao channelExposureDao;
    @Autowired
    ChannelDao channleDao;
    @Autowired
    UserDao userDao;
    @Autowired
    APPDao appDao;

    @Autowired
    IRecordService recordService;
    @Autowired
    UserChannelVisitDao userChannelVisitDao;




    /**
     * 获取首页信息
     * @param userGuid 用户的ID
     * @param IP  IP 地址
     * @return
     */
    @Override
    public HomeResponse getHomeInfo(String userGuid, String IP) {
        long start = System.currentTimeMillis();

        HomeResponse homeResponse = new HomeResponse();
        //获取用户信息
        if(StringUtils.isEmpty(userGuid)) {
            //如果用户为空给一个系统默认的用户ID
            userGuid = UserConstant.DEFAULT_USER;
        }
        UserInfoEntity userInfoEntity = userDao.queryUserInfoByID(userGuid);
        homeResponse.setUserInfoEntity(userInfoEntity);


        //获取频道信息
        //1. 获取用户的APP信息
        List<APPInfoEntity> appInfoEntityList = appDao.queryAppInfoByUserID(userGuid);
        if(appInfoEntityList.isEmpty()|| appInfoEntityList.size() == 0){
            homeResponse.setRetCode(ErrorConstant.NOAPPCODE);
            return homeResponse;
        }
        APPInfoEntity appInfoEntity = appInfoEntityList.get(0);
        //2. 默认取第一个APP 根据APPID 找到频道信息列表
        List<ChannelEntity> channelEntityList = channleDao.queryChannelByAppID(appInfoEntity.getAppGuid());
        if(channelEntityList.isEmpty()|| channelEntityList.size() == 0){
            homeResponse.setRetCode(ErrorConstant.NOCHANNEL_CODE);
            homeResponse.setRetMsg(ErrorConstant.NOCHANNEL_MESSAGE);
            return homeResponse;
        }
        homeResponse.setChannelEntityList(channelEntityList);

        //获取推荐频道的ID
        String channelGuID = null;
        for (ChannelEntity channelEntity:channelEntityList) {
            if(null!=channelEntity.getRecommand()&& channelEntity.getRecommand()){
                channelGuID = channelEntity.getChannelGuid();
            }
        }
        long artStart = System.currentTimeMillis();
        //获取推荐文章信息 (按时间倒序获取100条数据,排序规则是 时间倒序第一位，Recommend 第二位)
        List<ArticleInfoEntity> articleInfoEntityList = articleDao.queryTJArticleTOP100(channelGuID);

        //处理同刊占比不能超过30%
        List<ArticleInfoEntity> resultArticleList = this.handleArticleList(articleInfoEntityList);

        Long sysStart = System.currentTimeMillis();
        // 记录推荐的文章到文章推荐记录表(异步)
        recordService.recordTXArticle(channelGuID,IP,resultArticleList);
        long sysEnd = System.currentTimeMillis();

        // 记录频道访问记录到频道访问记录表（异步）
        recordService.recordChannelVist(channelGuID,userGuid,IP);


        // 打散排序
        resultArticleList = ListRandomUtils.randomList(resultArticleList);
        homeResponse.setArticleInfoEntityList(resultArticleList);
        homeResponse.setRetCode(ErrorConstant.SUCCESS);

        long end = System.currentTimeMillis();
        System.out.println("总耗时 ： "+(end-start));
        System.out.println("获取文章列表耗时 ： "+(end-artStart));
        System.out.println("记录文章推荐记录耗时 ： "+(sysEnd-sysStart));

        return homeResponse;
    }



    /**
     * 获取指定频道下的文章信息分页 ，按时间倒序排列
     * @param channelId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ArticleListResponse findArticeleByChannelID(String channelId, Date createTime, String IP, String userGuid,int pageNum, int pageSize) {
        long start = System.currentTimeMillis();
        ArticleListResponse articleListResponse = new ArticleListResponse();
        List<ArticleInfoEntity> articleInfoEntityList = new ArrayList<>();


        //当前频道是否是推荐频道
        ChannelEntity channelEntity = channleDao.queryChannelInfoByChannelGuid(channelId);
        if(null==channelEntity){
            articleListResponse.setRetCode(ErrorConstant.NOCHANNEL_CODE);
            articleListResponse.setRetMsg(ErrorConstant.NOCHANNEL_MESSAGE);
            return articleListResponse;
        }
        long artStart = System.currentTimeMillis();
        PageHelper.startPage(pageNum, 100);
        if(null!=channelEntity.getRecommand() && channelEntity.getRecommand()){
            //获取推荐频道数据， 时间小于createTime
            articleInfoEntityList = articleDao.queryTJArticleByCreateDate(channelId,createTime);
        }else{
            //获取当前频道数据，时间小于createTime
            articleInfoEntityList = articleDao.findArticeleByChannelIDAndCreateDate(channelId,createTime);
        }

        long handTime = System.currentTimeMillis();

        PageInfo result = new PageInfo(articleInfoEntityList);
        Long  total = result.getTotal();
        System.out.println(" 总条数为： " + total);
        articleListResponse.setTotal(total);
        //获取100条数据信息
        List<ArticleInfoEntity> resulist = result.getList();
        //处理同刊占比30%
        List<ArticleInfoEntity> articleInfoEntityListResult = this.handleArticleList(resulist);


        //记录推荐的文章到文章推荐记录表(异步)
        recordService.recordTXArticle(channelId,IP,articleInfoEntityListResult);


        // 打散排序
        articleInfoEntityListResult = ListRandomUtils.randomList(articleInfoEntityListResult);
        articleListResponse.setArticleInfoEntityList(articleInfoEntityListResult);
        articleListResponse.setRetCode(ErrorConstant.SUCCESS);

        //TODO 记录频道访问记录到频道访问记录表（异步）
        if(pageNum==1){
            recordService.recordChannelVist(channelId,userGuid,IP);
        }


        long end = System.currentTimeMillis();
        System.out.println("总耗时 ： "+(end-start));
        System.out.println("获取文章列表耗时 ： "+(handTime-artStart));
        System.out.println("处理文章结果耗时 ： "+(end-handTime));
        return articleListResponse;


    }

    /**
     * 获取特定频道下的最新的文章的信息
     * @param channelGuid
     * @param  IP
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ArticleListResponse findNewArticeleByChannelID(String channelGuid, String IP,String userGuid, int pageNum, int pageSize) {
        List<ArticleInfoEntity> articleInfoEntityList = new ArrayList<>();
        ArticleListResponse articleListResponse = new ArticleListResponse();

        //当前频道是否是推荐频道
        ChannelEntity channelEntity = channleDao.queryChannelInfoByChannelGuid(channelGuid);
        if(null==channelEntity){
            articleListResponse.setRetCode(ErrorConstant.NOCHANNEL_CODE);
            articleListResponse.setRetMsg(ErrorConstant.NOCHANNEL_MESSAGE);
            return articleListResponse;
        }


        //获取用户进入频道或最近拉新数据的时间
        UserChannelVisitLogEntity userChannelVisitLogEntity = userChannelVisitDao.queryChannelVisitInfo(channelGuid,IP,userGuid);
        Date lastVisitTime = userChannelVisitLogEntity.getLastVisitTime();
        if(null==lastVisitTime){
            System.out.println("第一次访问......");
            lastVisitTime = new Date();
        }

        List<ArticleInfoEntity> articleInfoEntityListTop100 = new ArrayList<>();
        if(null!=channelEntity.getRecommand() && channelEntity.getRecommand()){
            //根据IP和频道ID 找到最近推荐的一篇文章ID。 取10条按时间排列
            //List<ChannelExposureLogEntity> channelExposureLogEntityList = channelExposureDao.findChannelExposureByChangIDAndIP(channelGuid,IP);
            //if(channelExposureLogEntityList.size()>0){

                /*ChannelExposureLogEntity channelExposureLogEntity = channelExposureLogEntityList.get(0);
                //根据文章ID找到文章的时间
                List<ArticleInfoEntity> articleInfoList = articleDao.findArticleByTitleId(channelExposureLogEntity.getTitleID());
                ArticleInfoEntity articleInfoEntity = null;
                if(articleInfoList.size()>0){
                    articleInfoEntity = articleInfoList.get(0);
                }*/

                articleInfoEntityListTop100 = articleDao.queryTJArticleTOP100AndTime(channelGuid,lastVisitTime);
                articleInfoEntityList = this.handleArticleList(articleInfoEntityListTop100);
            //}

            //若没有，走老方法随机推荐10条
            if(articleInfoEntityList.size()<=9){
                articleInfoEntityList = articleDao.queryTJArticle(channelGuid);
            }


        }else{
            //根据IP和频道ID 找到最近推荐的一篇文章ID。 取10条按时间排列
            //List<ChannelExposureLogEntity> channelExposureLogEntityList = channelExposureDao.findChannelExposureByChangIDAndIP(channelGuid,IP);

            //if(channelExposureLogEntityList.size()>0){
                /*ChannelExposureLogEntity channelExposureLogEntity = channelExposureLogEntityList.get(0);
                //根据文章ID找到文章的时间
                List<ArticleInfoEntity> articleInfoList = articleDao.findArticleByTitleId(channelExposureLogEntity.getTitleID());
                ArticleInfoEntity articleInfoEntity = null;
                if(articleInfoList.size()>0){
                    articleInfoEntity = articleInfoList.get(0);
                }*/
                articleInfoEntityListTop100 = articleDao.findArticleInfoByChannelAndTime(channelGuid, lastVisitTime);
                articleInfoEntityList = this.handleArticleList(articleInfoEntityListTop100);
           // }

            //若没有，走老方法随机推荐10条
            if(articleInfoEntityList.size()<=0){
                articleInfoEntityList = articleDao.findArticleInfoByNewID(channelGuid);
            }

        }


        // 记录推荐的文章到文章推荐记录表(异步)
        recordService.recordTXArticle(channelGuid,IP,articleInfoEntityList);


        //TODO 记录频道访问记录到频道访问记录表（异步）

        if(pageNum==1){
            recordService.recordChannelVist(channelGuid,userGuid,IP);
        }

        // 打散排序
        articleInfoEntityList = ListRandomUtils.randomList(articleInfoEntityList);
        articleListResponse.setArticleInfoEntityList(articleInfoEntityList);
        articleListResponse.setRetCode(0);

        return articleListResponse;

    }



    /**
     * 查询文章详细信息根据文章的ID
     * @param titleId
     * @return
     */
    @Override
    public ArticleInfoEntity findArticle(String titleId,String IP,String userGuid,String channelID) {
        List<ArticleInfoEntity> articleInfoEntityList = articleDao.findArticleByTitleId(titleId);
        ArticleInfoEntity articleInfoEntity =  null;
        if(articleInfoEntityList.size()>0){
            articleInfoEntity =articleInfoEntityList.get(0);
        }
        // 文章访问记录加1(异步)
        recordService.updateVisitCountAsyn(titleId,articleInfoEntity);
        //记录文章访问记录(异步记录)
        recordService.recordArticleVisitInfo(IP,userGuid,articleInfoEntity,channelID);
        return articleInfoEntity;
    }



    /**
     * 处理文章数据，同刊占比不能超过30%
     * @param pageResultList
     * @return
     */
    private List<ArticleInfoEntity> handleArticleList(List<ArticleInfoEntity> pageResultList) {
        List<ArticleInfoEntity> articleInfoEntityList = new ArrayList<>();


        int i = 0;
        String name = "xxxx";
        int j = 1;
        Set<String> resultSet = new HashSet<>();
        for (ArticleInfoEntity articleInfoEntity : pageResultList) {
            if(!name.equalsIgnoreCase(articleInfoEntity.getMagazineName())){
                j = 1;
                name = articleInfoEntity.getMagazineName();
                i++;
            }else{
                if(!resultSet.contains(articleInfoEntity.getTitleID())){
                    if(j <= 3){
                        articleInfoEntityList.add(articleInfoEntity);
                        j++;
                        resultSet.add(articleInfoEntity.getTitleID());
                    }

                }
            }
            if(articleInfoEntityList.size()>=10){
                break;
            }
        }

        //处理没有图片的问题 默认给的是一个404找不到的图片， 可以设置默认图片后期
        //处理时间格式问题
        for (ArticleInfoEntity articleInfoEntity:articleInfoEntityList) {
            if(null == articleInfoEntity.getImgs()){
                String img = "[{\"url\":\"http://img1.qikan.com.cn/qkimages/hush/hush201802/hush2018020455-1-l.jpg\",\"width\":0,\"height\":0}]";
                articleInfoEntity.setImgs(img);
            }

        }

        return articleInfoEntityList;
    }

    public static void main(String[] args) {
        int i = 11/10;
        System.out.println(i);

    }
}
