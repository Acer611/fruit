package com.dragon.fruit.dao.fruit;


import com.dragon.fruit.entity.po.fruit.APPInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @dec APP 数据访问层
 * @author  Gaofei
 * @Date 2018-10-31
 */
@Mapper
public interface APPDao {

    /**
     * 获取APP信息根据用户的ID
     * @param userGuid
     * @return
     */
    @Select("SELECT Id AS Id ,AppGuid as appGuid,AppName as appName,UserId as userID," +
            "CreateDate as createDate,UpdateDate AS updateDate,CreateUser AS createUser," +
            "UpdateUser AS updateUser,Secret AS secret,Img AS img ,UpdateRule AS UpdateRule," +
            "IsEnable as isEnable,SecChannel AS secChannel " +
            "FROM App WHERE UserId = #{userGuid}")
    List<APPInfoEntity> queryAppInfoByUserID(String userGuid);
}
