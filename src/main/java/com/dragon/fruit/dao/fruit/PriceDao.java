package com.dragon.fruit.dao.fruit;

import com.dragon.fruit.entity.po.user.PriceConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 价格配置的信息表
 */

@Mapper
public interface PriceDao {

    /**
     * 查询所有的价格配置信息
     * @return
     */
    @Select("select * from priceConfig where status = 1")
    List<PriceConfig> list();


    /**
     * 查询价格配置详情信息
     * @param id
     * @return
     */
    @Select("select * from priceConfig where id = #{id}")
    PriceConfig detail(Long id);
}
