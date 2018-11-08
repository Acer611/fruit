package com.dragon.fruit.dao.fruit;


import com.dragon.fruit.dao.fruit.sqlprovider.TokenSQLProvider;
import com.dragon.fruit.entity.po.fruit.TokenEntity;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @dec 签名的数据处理层
 * @author  Gaofei
 * @date 2018-11-07
 *
 */

@Mapper
public interface TokenDao {

    /**
     * 新增一条token
     * @param tokenEntity
     */
    @Insert("insert into Token(ID,token,expiredTime,isDel, createTime,updateTime) " +
            " values(#{ID},#{token},#{expiredTime},#{delFlag},#{createTime},#{updateTime}" +
            ")" )
    public void insertToken(TokenEntity tokenEntity);


    /**
     * 查询token是否存在
     * @param token
     * @return
     */
    @Select("select ID,token,expiredTime,isDel as delFlag from Token where token=#{token}")
    public TokenEntity queryTokenByToken(String token);


    /**
     * 查询过当前时间过期的token集合
     * @param time
     * @return
     */
    @Select("select ID,token,expiredTime,isDel as delFlag from Token where expiredTime<#{time} and isDel=0")
    public List<TokenEntity> queryExpiredToken(@Param("time") Date time);

    /**
     * 更新token
     * @param tokenEntity
     */
    @UpdateProvider(type= TokenSQLProvider.class, method="updateToken")
    public void updateToken(TokenEntity tokenEntity);

    /**
     * 查询已经过期的Token
     * @return
     */
    @Select("select ID,token,expiredTime,isDel as delFlag from Token where isDel=1")
    List<TokenEntity> queryHasExpiredToken();

    /**
     * 批量删除Token
     * @param tokenEntityList
     */
    void batchDelToken(List<TokenEntity> tokenEntityList);
}
