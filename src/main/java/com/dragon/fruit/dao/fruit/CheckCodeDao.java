package com.dragon.fruit.dao.fruit;

import com.dragon.fruit.dao.fruit.sqlprovider.CheckCodeSQLProvider;
import com.dragon.fruit.entity.po.user.CheckCode;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CheckCodeDao {


    /**
     * 发送短信验证码时记录验证码信息
     */
    @Insert("insert into check_code(id,ip,msg_id,phone, check_code,create_at,expire_at,is_use,use_at,del_flag) " +
            " values(#{id},#{ip},#{msgId},#{phone},#{checkCode},#{createAt},#{expireAt},#{isUse},#{useAt},#{delFlag}" +
            ")" )
    void save(CheckCode checkCode);

    /**
     * 根据手机号查询验证码信息
     * @param phone
     * @return
     */
    @Select("SELECT id,ip,msg_id,phone,check_code,create_at,expire_at,is_use,use_at,del_flag FROM check_code WHERE phone =#{phone};")
    @Results(value ={
            @Result(id=true, property="id",column="id"),
            @Result(property="ip",column="ip"),
            @Result(property="phone",column="phone"),
            @Result(property="checkCode",column="check_code"),
            @Result(property="createAt",column="create_at"),
            @Result(property="expireAt",column="expire_at"),
            @Result(property="isUse",column="is_use"),
            @Result(property="useAt",column="use_at"),
            @Result(property="delFlag",column="del_flag"),
            @Result(property="msgId",column="msg_id")
    })
    CheckCode findCheckCodeBYPhone(String phone);

    /**
     * 删除数据信息
     * @param phone
     */
    @Delete(" DELETE FROM check_code WHERE phone =#{phone}")
    void deleteCheckCodeByPhone(String phone);

    /**
     * 更新使用状态等信息
     * @param checkCode
     */
    @UpdateProvider(type= CheckCodeSQLProvider.class, method="updateCheckCode")
    void updateCheckCode(CheckCode checkCode);
}
