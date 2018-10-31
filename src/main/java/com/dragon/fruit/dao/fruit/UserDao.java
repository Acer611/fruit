package com.dragon.fruit.dao.fruit;


import com.dragon.fruit.entity.po.fruit.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @des 用户信息操作类
 * @author  Gaofei
 * @Date 2018-10-31
 */
@Mapper
public interface UserDao {


    /**
     * 根据用户ID 查找用户信息
     * @param userGuid
     * @return
     */
    @Select("SELECT  UserGuid as userGuid,LoginName as loginName,CreateDate as createDate ,UserType as userType,LoginTimes as loginTime,\n" +
            "CompanyName as companyName,Contacts as contacts,WinXin as weiXin ,QQ as QQ,Phno as phno ,Email as Email \n" +
            "FROM UserInfo WHERE UserGuid = #{userGuid}")
    UserInfoEntity queryUserInfoByID(String userGuid);
}
