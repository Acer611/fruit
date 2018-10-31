package com.dragon.fruit.service.impl;

import com.dragon.fruit.common.constant.ErrorConstant;
import com.dragon.fruit.common.constant.UserConstant;
import com.dragon.fruit.dao.fruit.*;
import com.dragon.fruit.entity.po.fruit.*;
import com.dragon.fruit.entity.vo.response.ArticleFVResponse;
import com.dragon.fruit.service.IArticleService;
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


    /**
     * 获取指定频道下的文章信息分页 ，按时间倒序排列
     * @param channelId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<ArticleInfoEntity> findArticeleByChannelID(String channelId, int pageNum, int pageSize) {
        List<ArticleInfoEntity> articleInfoEntityList = new ArrayList<>();

        //根据IP和频道ID 找到最近推荐的一篇文章ID。 取10条按时间排列
        List<ChannelExposureLogEntity> channelExposureLogEntityList = channelExposureDao.findChannelExposureByChangIDAndIP(channelId,"0:0:0:0:0:0:0:1");
        //当前频道是否是推荐频道
        ChannelEntity channelEntity = channleDao.queryChannelInfoByChannelGuid(channelId);
        PageHelper.startPage(pageNum, 100);
        if(channelExposureLogEntityList.size()<=0){
            if(channelEntity.getRecommand()){
                //获取推荐文章信息，按时间倒序
                articleInfoEntityList = articleDao.queryTJArticle(channelId);
            }else{
                //获取特定频道下的数据
                articleInfoEntityList = articleDao.findArticeleByChannelID(channelId);
            }
        }else{
            ChannelExposureLogEntity channelExposureLogEntity = channelExposureLogEntityList.get(0);
            //根据文章ID找到文章的时间
            ArticleInfoEntity articleInfoEntity = articleDao.findArticleByTitleId(channelExposureLogEntity.getTitleID());

            if(null!= channelEntity.getRecommand()&& channelEntity.getRecommand()){

                articleInfoEntityList = articleDao.queryTJArticleByCreateDate(channelId,null== articleInfoEntity ?new Date(): articleInfoEntity.getCreateDate());
            }else{
                articleInfoEntityList = articleDao.findArticeleByChannelIDAndCreateDate(channelId,null== articleInfoEntity ?new Date(): articleInfoEntity.getCreateDate());
            }

        }

        if(null== articleInfoEntityList || articleInfoEntityList.size()<=0){
            return  new ArrayList<>();
        }
        //int newpageNum = pageNum/6 +1;

        //按时间倒序取出当前频道的100篇文章

        PageInfo result = new PageInfo(articleInfoEntityList);
        List<ArticleInfoEntity> resulist = result.getList();
        List<ArticleInfoEntity> articleInfoEntityListResult = new ArrayList<>();
        articleInfoEntityListResult = this.handleArticleList(resulist);

        return articleInfoEntityListResult;
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
    public List<ArticleInfoEntity> findNewArticeleByChannelID(String channelGuid, String IP, int pageNum, int pageSize) {
        List<ArticleInfoEntity> articleInfoEntityList = new ArrayList<>();
        //当前频道是否是推荐频道
        ChannelEntity channelEntity = channleDao.queryChannelInfoByChannelGuid(channelGuid);
        if(channelEntity.getRecommand()){
            List<ArticleInfoEntity> articleInfoEntityListTop100 = new ArrayList<>();
            //根据IP和频道ID 找到最近推荐的一篇文章ID。 取10条按时间排列
            List<ChannelExposureLogEntity> channelExposureLogEntityList = channelExposureDao.findChannelExposureByChangIDAndIP(channelGuid,IP);
            if(channelExposureLogEntityList.size()>0){
                ChannelExposureLogEntity channelExposureLogEntity = channelExposureLogEntityList.get(0);
                //根据文章ID找到文章的时间
                ArticleInfoEntity articleInfoEntity = articleDao.findArticleByTitleId(channelExposureLogEntity.getTitleID());
                articleInfoEntityListTop100 = articleDao.queryTJArticleTOP100AndTime(channelGuid, articleInfoEntity.getCreateDate());
            }

            //按时间倒序获取推荐频道的文章
             articleInfoEntityListTop100 = articleDao.queryTJArticleTOP100(channelGuid);
             articleInfoEntityList = this.handleArticleList(articleInfoEntityListTop100);


        }else{

            //根据IP和频道ID 找到最近推荐的一篇文章ID。 取10条按时间排列
            List<ChannelExposureLogEntity> channelExposureLogEntityList = channelExposureDao.findChannelExposureByChangIDAndIP(channelGuid,"0:0:0:0:0:0:0:1");

            if(channelExposureLogEntityList.size()<=0){
                //初次进来取最新的10条记录
                articleInfoEntityList = articleDao.findArticleInfoTop10(channelGuid);
                return articleInfoEntityList;
            }
            ChannelExposureLogEntity channelExposureLogEntity = channelExposureLogEntityList.get(channelExposureLogEntityList.size()-1);
            //根据文章ID找到文章的时间
            ArticleInfoEntity articleInfoEntity = articleDao.findArticleByTitleId(channelExposureLogEntity.getTitleID());
            //查找当前频道下有没有大于上次推荐时间的文章
            articleInfoEntityList = articleDao.findArticleInfoByChannelAndTime(channelGuid, articleInfoEntity.getCreateDate());
            //若没有，走老方法随机推荐10条
            if(articleInfoEntityList.size()<=0){
                articleInfoEntityList = articleDao.findArticleInfoByNewID(channelGuid);
            }
            //若有取10条返回
        }


        return articleInfoEntityList;
    }

    /**
     * 查询文章详细信息根据文章的ID
     * @param titleId
     * @return
     */
    @Override
    public ArticleInfoEntity findArticle(String titleId) {
        return articleDao.findArticleByTitleId(titleId);
    }

    /**
     * 获取首页信息
     * @param userGuid 用户的ID
     * @return
     */
    @Override
    public ArticleFVResponse getHomeInfo(String userGuid) {
        ArticleFVResponse articleFVResponse = new ArticleFVResponse();
        //获取用户信息
       if(StringUtils.isEmpty(userGuid)) {
           //如果用户为空给一个系统默认的用户ID
           userGuid = UserConstant.DEFAULT_USER;
       }
        UserInfoEntity userInfoEntity = userDao.queryUserInfoByID(userGuid);
        articleFVResponse.setUserInfoEntity(userInfoEntity);


        //获取频道信息
             //1. 获取用户的APP信息
        List<APPInfoEntity> appInfoEntityList = appDao.queryAppInfoByUserID(userGuid);
        if(appInfoEntityList.isEmpty()||appInfoEntityList.size()==0){
            articleFVResponse.setRetCode(ErrorConstant.NOAPPCODE);
            return  articleFVResponse;
        }
        APPInfoEntity appInfoEntity = appInfoEntityList.get(0);
            //2. 默认取第一个APP 根据APPID 找到频道信息列表
        List<ChannelEntity> channelEntityList = channleDao.queryChannelByAppID(appInfoEntity.getAppGuid());
        if(channelEntityList.isEmpty()||channelEntityList.size()==0){
            articleFVResponse.setRetCode(ErrorConstant.NOCHANNEL_CODE);
            articleFVResponse.setRetMsg(ErrorConstant.NOCHANNEL_MESSAGE);
            return  articleFVResponse;
        }
        articleFVResponse.setChannelEntityList(channelEntityList);


        //找到推荐频道的GuID
        String channelGuID = null;
        for (ChannelEntity channelEntity:channelEntityList) {
            if(null!=channelEntity.getRecommand()&& channelEntity.getRecommand()){
                channelGuID = channelEntity.getChannelGuid();
            }
        }
        long artStart = System.currentTimeMillis();
        //获取推荐文章信息 (按时间倒序获取10条数据)
        //PageHelper.startPage(1, 100);
        List<ArticleInfoEntity> articleInfoEntityList = articleDao.queryTJArticleTOP100(channelGuID);
        //PageInfo pageResultList = new PageInfo(articleInfoEntityList);
        //List<ArticleInfoEntity> pageList = pageResultList.getList();
        List<ArticleInfoEntity> resultArticleList = this.handleArticleList(articleInfoEntityList);
        articleFVResponse.setArticleInfoEntityList(resultArticleList);


        //TODO 记录推荐的文章到文章推荐记录表(异步)
        return articleFVResponse;
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
        return articleInfoEntityList;
    }

    public static void main(String[] args) {
        int i = 11/10;
        System.out.println(i);

    }
}
