package com.dragon.fruit.service.impl;

import com.dragon.fruit.dao.fruit.ArticleDao;
import com.dragon.fruit.dao.fruit.ChannelDao;
import com.dragon.fruit.dao.fruit.ChannelExposureDao;
import com.dragon.fruit.entity.po.fruit.ArticleInfo;
import com.dragon.fruit.entity.po.fruit.Channel;
import com.dragon.fruit.entity.po.fruit.ChannelExposureLog;
import com.dragon.fruit.service.IArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    /**
     * 获取指定频道下的文章信息分页 ，按时间倒序排列
     * @param channelId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<ArticleInfo> findArticeleByChannelID(String channelId, int pageNum, int pageSize) {
        List<ArticleInfo> articleInfoList = new ArrayList<>();

        //根据IP和频道ID 找到最近推荐的一篇文章ID。 取10条按时间排列
        List<ChannelExposureLog> channelExposureLogList = channelExposureDao.findChannelExposureByChangIDAndIP(channelId,"0:0:0:0:0:0:0:1");
        //当前频道是否是推荐频道
        Channel channel = channleDao.queryChannelInfoByChannelGuid(channelId);
        PageHelper.startPage(pageNum, 80);
        if(channelExposureLogList.size()<=0){
            if(channel.getIsRecommand()){
                //获取推荐文章信息，按时间倒序
                articleInfoList = articleDao.queryTJArticle(channelId);
            }else{
                //获取特定频道下的数据
                articleInfoList = articleDao.findArticeleByChannelID(channelId);
            }
        }else{
            ChannelExposureLog channelExposureLog = channelExposureLogList.get(0);
            //根据文章ID找到文章的时间
            ArticleInfo articleInfo = articleDao.findArticleByTitleId(channelExposureLog.getTitleID());

            if(null!=channel.getIsRecommand()&&channel.getIsRecommand()){

                articleInfoList = articleDao.queryTJArticleByCreateDate(channelId,null==articleInfo?new Date():articleInfo.getCreateDate());
            }else{
                articleInfoList = articleDao.findArticeleByChannelIDAndCreateDate(channelId,null==articleInfo?new Date():articleInfo.getCreateDate());
            }

        }

        if(null==articleInfoList||articleInfoList.size()<=0){
            return  new ArrayList<>();
        }
        //int newpageNum = pageNum/6 +1;

        //按时间倒序取出当前频道的80篇文章


        PageInfo result = new PageInfo(articleInfoList);
        List resulist = result.getList();
        int i = 0;
        List<ArticleInfo> articleInfoListResult = new ArrayList<>();
        String name = "xxxx";
        int j = 1;

        //循环80篇文章，找到对应的期刊名，若为不同期刊，放到返回的集合中若为同一期刊集合中不能超过三本
        Set<String> resultSet = new HashSet<>();
        for (Object articleInfo: resulist) {

            ArticleInfo articleInfo1 = (ArticleInfo) articleInfo;
            if(!name.equalsIgnoreCase(articleInfo1.getMagazineName())){
                j=1;
                name = articleInfo1.getMagazineName();
                i++;
            }else{
                if(!resultSet.contains(articleInfo1.getTitleID())){
                    if(j<=3){
                        articleInfoListResult.add(articleInfo1);
                        j++;
                        resultSet.add(articleInfo1.getTitleID());
                    }

                }
            }
            if(articleInfoListResult.size()>=10){
                break;
            }
        }

        System.out.println("一共几种杂志"+i);
        return articleInfoListResult;
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
    public List<ArticleInfo> findNewArticeleByChannelID(String channelGuid, String IP, int pageNum, int pageSize) {
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        //当前频道是否是推荐频道
        Channel channel = channleDao.queryChannelInfoByChannelGuid(channelGuid);
        if(channel.getIsRecommand()){
            List<ArticleInfo> articleInfoListTop100 = new ArrayList<>();
            //根据IP和频道ID 找到最近推荐的一篇文章ID。 取10条按时间排列
            List<ChannelExposureLog> channelExposureLogList = channelExposureDao.findChannelExposureByChangIDAndIP(channelGuid,IP);
            if(channelExposureLogList.size()>0){
                ChannelExposureLog channelExposureLog = channelExposureLogList.get(0);
                //根据文章ID找到文章的时间
                ArticleInfo articleInfo = articleDao.findArticleByTitleId(channelExposureLog.getTitleID());
                articleInfoListTop100 = articleDao.queryTJArticleTOP100AndTime(channelGuid,articleInfo.getCreateDate());
            }

            //按时间倒序获取推荐频道的文章
             articleInfoListTop100 = articleDao.queryTJArticleTOP100(channelGuid);

            int i = 0;
            String name = "xxxx";
            int j = 1;
            Set<String> resultSet = new HashSet<>();
            for (ArticleInfo articleInfo:articleInfoListTop100) {
                if(!name.equalsIgnoreCase(articleInfo.getMagazineName())){
                    j=1;
                    name = articleInfo.getMagazineName();
                    i++;
                }else{
                    if(!resultSet.contains(articleInfo.getTitleID())){
                        if(j<=3){
                            articleInfoList.add(articleInfo);
                            j++;
                            resultSet.add(articleInfo.getTitleID());
                        }

                    }
                }
                if(articleInfoList.size()>=10){
                    break;
                }
            }

        }else{

            //根据IP和频道ID 找到最近推荐的一篇文章ID。 取10条按时间排列
            List<ChannelExposureLog> channelExposureLogList = channelExposureDao.findChannelExposureByChangIDAndIP(channelGuid,"0:0:0:0:0:0:0:1");

            if(channelExposureLogList.size()<=0){
                //初次进来取最新的10条记录
                articleInfoList = articleDao.findArticleInfoTop10(channelGuid);
                return  articleInfoList;
            }
            ChannelExposureLog channelExposureLog = channelExposureLogList.get(channelExposureLogList.size()-1);
            //根据文章ID找到文章的时间
            ArticleInfo articleInfo = articleDao.findArticleByTitleId(channelExposureLog.getTitleID());
            //查找当前频道下有没有大于上次推荐时间的文章
            articleInfoList = articleDao.findArticleInfoByChannelAndTime(channelGuid,articleInfo.getCreateDate());
            //若没有，走老方法随机推荐10条
            if(articleInfoList.size()<=0){
                articleInfoList = articleDao.findArticleInfoByNewID(channelGuid);
            }
            //若有取10条返回
        }


        return articleInfoList;
    }

    /**
     * 查询文章详细信息根据文章的ID
     * @param titleId
     * @return
     */
    @Override
    public ArticleInfo findArticle(String titleId) {
        return articleDao.findArticleByTitleId(titleId);
    }

    public static void main(String[] args) {
        int i = 11/10;
        System.out.println(i);

    }
}
