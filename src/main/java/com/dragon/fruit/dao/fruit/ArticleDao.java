package com.dragon.fruit.dao.fruit;


import com.dragon.fruit.dao.fruit.sqlprovider.ArticleVisitSQLProvider;
import com.dragon.fruit.entity.po.fruit.ArticleInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.Date;
import java.util.List;

/**
 * 文章数据处理成接口
 * @author Gaofei
 * @date  2018-10-24
 */
@Mapper
public interface ArticleDao {

    /**
     * 查询频道下的文章信息
     * @param channelId
     * @return
     */
    @Select("SELECT " +
            "a.TitleID," +
            "a.ID," +
            "a.CategoryCode," +
            "a.Title," +
            "a.CreateDate ," +
            "a.Author," +
            "a.MagazineName," +
            "a.Year," +
            "a.Issue," +
            "a.Columns," +
            "a.ArticleCreateDate," +
            "a.UpdateDate," +
            "a.Abstract," +
            "a.WordSize," +
            "a.Rank," +
            "a.IsOnline," +
            "a.ArticleContent," +
            "a.Imgs," +
            "a.HasImage," +
            "a.Recommend," +
            "a.Keyword," +
            "a.Simgs," +
            "a.Oprator " +
            "FROM " +
            "ArticleInfo AS a " +
            "LEFT JOIN UserArticle AS ua ON a.TitleID= ua.TitleID " +
            "WHERE " +
            "ua.ChannelGuid= #{channelId} " +
            "ORDER BY " +
            "a.CreateDate DESC")
    public List<ArticleInfoEntity> findArticeleByChannelID(String channelId);




    /**
     * 根据TitleID查找文章信息
     * @param titleID
     * @return
     */
    @Select("SELECT " +
            "a.TitleID as titleID ," +
            "a.ID as iD," +
            "a.CategoryCode as categoryCode," +
            "a.Title as title," +
            "a.CreateDate as createDate," +
            "a.Author as author," +
            "a.MagazineName as magazineName," +
            "a.Year as year," +
            "a.Issue as issue," +
            "a.Columns as columns," +
            "a.ArticleCreateDate as articleCreateDate," +
            "a.UpdateDate as updateDate," +
            "a.Abstract as abstractName," +
            "a.WordSize as wordSize," +
            "a.Rank as rank," +
            "a.IsOnline as isOnline," +
            "a.ArticleContent as articleContent," +
            "a.Imgs as imgs," +
            "a.HasImage as hasImage," +
            "a.Recommend as recommend ," +
            "a.Keyword as keyword," +
            "a.Simgs as simgs," +
            "a.VisitCount as visitCount," +
            "a.Oprator as oprator" +
            " from ArticleInfo AS a WHERE TitleID= #{titleID}")
    List<ArticleInfoEntity> findArticleByTitleId(String titleID);

    /**
     * 根据时间查找频道下的信息
     * @param channelGuid
     * @param createDate
     * @return
     */

    @Select("SELECT TOP 10 " +
            "a.TitleID as titleID ," +
            "a.ID as iD," +
            "a.CategoryCode as categoryCode," +
            "a.Title as title," +
            "a.CreateDate as createDate," +
            "a.Author as author," +
            "a.MagazineName as magazineName," +
            "a.Year as year," +
            "a.Issue as issue," +
            "a.Columns as columns," +
            "a.ArticleCreateDate as articleCreateDate," +
            "a.UpdateDate as updateDate," +
            "a.Abstract as abstractName," +
            "a.WordSize as wordSize," +
            "a.Rank as rank," +
            "a.IsOnline as isOnline," +
            "a.ArticleContent as articleContent," +
            "a.Imgs as imgs," +
            "a.HasImage as hasImage," +
            "a.Recommend as recommend ," +
            "a.Keyword as keyword," +
            "a.Simgs as simgs," +
            "a.VisitCount as visitCount," +
            "a.Oprator as oprator " +
            "FROM " +
            "ArticleInfo AS a " +
            "LEFT JOIN UserArticle AS ua ON a.TitleID= ua.TitleID " +
            "WHERE " +
            "ua.ChannelGuid= #{channelGuid} and a.HasImage = 1 AND a.CreateDate > #{createDate} " +
            " ORDER BY " +
            "a.CreateDate DESC")
    List<ArticleInfoEntity> findArticleInfoByChannelAndTime(@Param("channelGuid") String channelGuid, @Param("createDate") Date createDate);

    /**
     * 随机获取当前频道的10条记录
     * @param channelGuid
     * @return
     */
    @Select("SELECT TOP 10 " +
            "a.TitleID, " +
            "a.ID," +
            "a.CategoryCode," +
            "a.Title," +
            "a.CreateDate ," +
            "a.Author," +
            "a.MagazineName," +
            "a.Year," +
            "a.Issue," +
            "a.Columns," +
            "a.ArticleCreateDate," +
            "a.UpdateDate," +
            "a.Abstract," +
            "a.WordSize," +
            "a.Rank," +
            "a.IsOnline," +
            "a.ArticleContent," +
            "a.Imgs," +
            "a.HasImage," +
            "a.Recommend," +
            "a.Keyword," +
            "a.Simgs," +
            "a.Oprator," +
            "ua.ChannelGuid " +
            "FROM " +
            "ArticleInfo AS a " +
            "LEFT JOIN UserArticle AS ua ON a.TitleID= ua.TitleID " +
            "WHERE " +
            "ua.ChannelGuid= #{channelGuid} and a.HasImage = 1 " +
            "ORDER BY " +
            "NEWID()")
    List<ArticleInfoEntity> findArticleInfoByNewID(String channelGuid);

    /**
     * 获取频道下最新的10条数据
     * @param channelGuid
     * @return
     */
    @Select("SELECT TOP 10 " +
            "a.TitleID, " +
            "a.ID," +
            "a.CategoryCode," +
            "a.Title," +
            "a.CreateDate ," +
            "a.Author," +
            "a.MagazineName," +
            "a.Year," +
            "a.Issue," +
            "a.Columns," +
            "a.ArticleCreateDate," +
            "a.UpdateDate," +
            "a.Abstract," +
            "a.WordSize," +
            "a.Rank," +
            "a.IsOnline," +
            "a.ArticleContent," +
            "a.Imgs," +
            "a.HasImage," +
            "a.Recommend," +
            "a.Keyword," +
            "a.Simgs," +
            "a.Oprator," +
            "ua.ChannelGuid " +
            "FROM " +
            "ArticleInfo AS a " +
            "LEFT JOIN UserArticle AS ua ON a.TitleID= ua.TitleID " +
            "WHERE " +
            "ua.ChannelGuid= #{channelGuid} and a.HasImage = 1" +
            "ORDER BY " +
            "a.CreateDate DESC")
    List<ArticleInfoEntity> findArticleInfoTop10(String channelGuid);

    /**
     * 获取推荐频道的文章Top100
     * @param channelGuid
     * @return
     */
    @Select("select TOP 100 a.*,p.UserId  from ArticleInfo a  " +
            "left join UserArticle u on u.TitleID = a.TitleID    " +
            "LEFT JOIN App p on p.UserId = u.UserGuid  " +
            "LEFT JOIN Channel c on c.AppId = p.AppGuid  " +
            "WHERE c.ChannelGuid=#{channelGuid}  and a.HasImage = 1  " +
            "order by a.CreateDate DESC ,a.Recommend DESC")
    List<ArticleInfoEntity> queryTJArticleTOP100(String channelGuid);

    /**
     * 随机获取10条推荐文章
     * @param channelId
     * @return
     */
    @Select("select Top 10 a.*,p.UserId  from ArticleInfo a  " +
            "left join UserArticle u on u.TitleID = a.TitleID    " +
            "LEFT JOIN App p on p.UserId = u.UserGuid  " +
            "LEFT JOIN Channel c on c.AppId = p.AppGuid  " +
            "WHERE c.ChannelGuid=#{channelGuid}  and a.HasImage = 1  " +
            " ORDER BY " +
            "NEWID()")
    List<ArticleInfoEntity> queryTJArticle(String channelId);

    /**
     * 倒序获取推荐文章且时间小于特定时间
     * @param channelId
     * @return
     */
   /* @Select("select  " +
            " a.TitleID," +
            " a.ID," +
            " a.CategoryCode," +
            " a.Title," +
            " a.CreateDate ," +
            " a.Author," +
            " a.MagazineName," +
            " a.Year," +
            " a.Issue," +
            " a.Columns," +
            " a.ArticleCreateDate," +
            " a.UpdateDate," +
            " a.Abstract," +
            " a.WordSize," +
            " a.Rank," +
            " a.IsOnline," +
            " a.ArticleContent," +
            " a.Imgs," +
            " a.HasImage," +
            " a.Recommend," +
            " a.Keyword," +
            " a.Simgs," +
            " a.Oprator," +
            " p.UserId  from ArticleInfo a  " +
            " left join UserArticle u on u.TitleID = a.TitleID    " +
            " LEFT JOIN App p on p.UserId = u.UserGuid  " +
            " LEFT JOIN Channel c on c.AppId = p.AppGuid  " +
            " WHERE c.ChannelGuid=#{channelId}  and a.HasImage = 1 and a.CreateDate < #{createDate} " +
            " order by a.CreateDate DESC ,a.Recommend DESC")*/
   @Select("select  a.TitleID, " +
           "             a.ID,  " +
           "             a.CategoryCode,  " +
           "             a.Title,  " +
           "             a.CreateDate ,  " +
           "             a.Author,  " +
           "             a.MagazineName,  " +
           "             a.Year,  " +
           "             a.Issue,  " +
           "             a.Columns,  " +
           "             a.ArticleCreateDate,  " +
           "             a.UpdateDate,  " +
           "             a.Abstract,  " +
           "             a.WordSize,  " +
           "             a.Rank,  " +
           "             a.IsOnline,  " +
           "             a.Imgs,  " +
           "             a.HasImage,  " +
           "             a.Recommend,  " +
           "             a.Keyword,  " +
           "             a.Simgs,u.UserGuid as UserId  from ArticleInfo a    " +
           "             RIGHT join UserArticle u on u.TitleID = a.TitleID    and u.UserGuid= #{userGuid}  " +
           "             WHERE a.HasImage = 1 and a.CreateDate < #{createDate} " +
           "             order by a.CreateDate DESC ,a.Recommend DESC")
    List<ArticleInfoEntity> queryTJArticleByCreateDate(@Param("userGuid") String userGuid, @Param("createDate") Date createDate);

    /**
     * 获取特定频道下且时间小于特定时间的文章
     * @param channelId
     * @param createDate
     * @return
     */
    @Select("SELECT " +
            " a.TitleID," +
            " a.ID," +
            " a.CategoryCode," +
            " a.Title," +
            " a.CreateDate ," +
            " a.Author," +
            " a.MagazineName," +
            " a.Year," +
            " a.Issue," +
            " a.Columns," +
            " a.ArticleCreateDate," +
            " a.UpdateDate," +
            " a.Abstract," +
            " a.WordSize," +
            " a.Rank," +
            " a.IsOnline," +
            " a.ArticleContent," +
            " a.Imgs," +
            " a.HasImage," +
            " a.Recommend," +
            " a.Keyword," +
            " a.Simgs," +
            " a.Oprator," +
            " ua.ChannelGuid " +
            " FROM " +
            " ArticleInfo AS a " +
            " LEFT JOIN UserArticle AS ua ON a.TitleID= ua.TitleID " +
            " WHERE " +
            " ua.ChannelGuid= #{channelId} and a.HasImage = 1 and a.CreateDate<#{createDate} " +
            " ORDER BY " +
            " a.CreateDate DESC")
    List<ArticleInfoEntity> findArticeleByChannelIDAndCreateDate(@Param("channelId") String channelId, @Param("createDate") Date createDate);


    @Select("select TOP 100 a.*,p.UserId  from ArticleInfo a  " +
            "left join UserArticle u on u.TitleID = a.TitleID    " +
            "LEFT JOIN App p on p.UserId = u.UserGuid  " +
            "LEFT JOIN Channel c on c.AppId = p.AppGuid  " +
            "WHERE c.ChannelGuid=#{channelGuid}  and a.HasImage = 1  and a.CreateDate>#{createDate} " +
            "order by a.CreateDate DESC ,a.Recommend DESC")
    List<ArticleInfoEntity> queryTJArticleTOP100AndTime(@Param("channelGuid") String channelGuid, @Param("createDate") Date createDate);

    @UpdateProvider(type= ArticleVisitSQLProvider.class, method="updateVisitCount")
    void updateVisitCount(ArticleInfoEntity articleInfoEntity);
}
