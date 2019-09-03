package com.dragon.fruit.dao.fruit;


import com.dragon.fruit.dao.fruit.sqlprovider.CheckCodeSQLProvider;
import com.dragon.fruit.entity.po.fruit.UserInfoEntity;
import com.dragon.fruit.entity.po.user.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    /**
     * 根据手机号查找用户信息
     * @param phone
     * @return
     */
    @Select("select * from user where phone=#{phone}")
    User findUserByPhone(String phone);

    /**
     * 插入用户数据信息
     * @param user
     */
    @Insert("  insert into user(phone,password,nickName,imageUrl,driveCode,isVip,expiredDate,status,createTime,pid)\n" +
            " values(#{phone},#{password},#{nickName},#{imageUrl},#{driveCode},#{isVip},#{expiredDate},#{status},#{createTime},#{pid})")
    void insertUserInfo(User user);

    /**
     * 根据邀请码查找用户信息
     * @param askCode
     * @return
     */
    @Select("select * from user where askCode=#{askCode}")
    User findUserByAskCode(String askCode);

    /**
     * 登录验证
     * @param user
     * @return
     */
    @Select("select * from user where phone=#{phone} and password = #{password} ")
    User login(User user);

    /**
     * 根据iD查找用户信息
     * @param userID
     * @return
     */
    @Select("select *,p.name AS tcName,p.id AS tcId from user u LEFT JOIN priceconfig p ON u.isVip=p.id  where u.id=#{userID}")
    @Results(value ={
            @Result(id=true, property="id",column="id"),
            @Result(property="phone",column="phone"),
            @Result(property="password",column="password"),
            @Result(property="nickName",column="nickName"),
            @Result(property="imageUrl",column="imageUrl"),
            @Result(property="driveCode",column="driveCode"),
            @Result(property="isVip",column="isVip"),
            @Result(property="expiredDate",column="expiredDate"),
            @Result(property="status",column="status"),
            @Result(property="checkCod",column="checkCod"),
            @Result(property="askCode",column="askCode"),
            @Result(property="pid",column="pid"),
            @Result(property="createTime",column="createTime"),
            @Result(property="uuid",column="uuid"),
            @Result(property="tcID",column="tcID"),
            @Result(property="tcName",column="tcName")
    })
    User findUserByID(Long userID);

    /**
     * 生成邀请码
     * @param user
     */
    @UpdateProvider(type= CheckCodeSQLProvider.class, method="generateAskCode")
    void generateAskCode(User user);

    /**
     * 修改用户信息
     * @param fatherUser
     */
    @UpdateProvider(type= CheckCodeSQLProvider.class, method="updateUser")
    void updateUser(User fatherUser);


    /**
     * 查找过期用户列表
     * @return
     */
    @Select("SELECT * FROM `user` WHERE NOW()>expiredDate AND isVip!=0")
    List<User> findExpiredUser();
}
