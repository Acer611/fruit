package com.dragon.fruit.dao.fruit;


import com.dragon.fruit.dao.fruit.sqlprovider.ArticleVisitSQLProvider;
import com.dragon.fruit.entity.po.fruit.UserArticleInfoVisitLogEntity;
import org.apache.ibatis.annotations.*;

/**
 * @des 文章访问记录数据处理层
 * @author  Gaofei
 * @date 2018-11-03
 */
@Mapper
public interface UserArticleVisitDao {
    /**
     * 查找是否有访问记录
     * @param title
     * @param ip
     * @param userGuid
     * @return
     */
    @Select("SELECT ID,UserId,TitleId,VisitCount FROM UserArticleVisitLog where IP=#{ip} AND TitleId =#{titleID} AND UserId=#{userGuid} ")
    UserArticleInfoVisitLogEntity findInfoByTitleId(@Param("titleID") String titleID, @Param("ip") String ip, @Param("userGuid") String userGuid);

    /**
     * 插入用户访问记录
     * @param userArticleInfoVisitLogEntity
     */
    @Insert("insert into UserArticleVisitLog(ID,UserId,IP,TitleId,VisitCount,IsDel,Title,ChannelId,VisitTime) " +
            " values(#{ID},#{userId},#{IP},#{titleID},#{visitCount},#{isDel},#{title},#{channelID},#{visitTime}" +
            ")" )
    void insertUserarticleVist(UserArticleInfoVisitLogEntity userArticleInfoVisitLogEntity);


    /**
     * 修改访问记录
     * @param userArticleInfoVisitLogEntity
     */
    @UpdateProvider(type= ArticleVisitSQLProvider.class, method="updateUserVistInfo")
    @Results(value ={
            @Result(id=true, property="ID",column="ID"),
            @Result(property="userId",column="UserId"),
            @Result(property="IP",column="IP"),
            @Result(property="titleID",column="TitleId"),
            @Result(property="visitCount",column="VisitCount"),
            @Result(property="isDel",column="IsDel"),
            @Result(property="title",column="Title"),
            @Result(property="channelId",column="ChannelId"),
            @Result(property="visitTime",column="VisitTime")
    })
    void updateUserVistInfo(UserArticleInfoVisitLogEntity userArticleInfoVisitLogEntity);
}
