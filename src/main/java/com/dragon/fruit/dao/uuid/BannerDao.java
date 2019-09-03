package com.dragon.fruit.dao.uuid;

import com.dragon.fruit.entity.po.uuid.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * banner处理层
 */
@Mapper
public interface BannerDao {

    @Select("select * from banner")
    List<Banner> list();
}
