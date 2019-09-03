package com.dragon.fruit.dao.uuid;

import com.dragon.fruit.entity.po.uuid.UUID;
import com.dragon.fruit.entity.po.uuid.UserUUID;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * UUID数据操作层
 */
@Mapper
public interface UUIDDao {
    /**
     * 获取当前线路下为使用的UUID
     * @param id
     * @return
     */
    @Select("SELECT * FROM uuid where vmessID =#{id} AND isEnable = 0 LIMIT 1")
    UUID findUUID(Long id);

    /**
     * 保存用户和UUID的关联关系
     * @param userUUID
     */
    @Insert(" insert into user_uuid(uuid,userID,isVip,uid,createDate,vmessID) " +
            " values(#{uuid},#{userID},#{isVip},#{uid},#{createDate},#{vmessID})")
    void saveUserUUID(UserUUID userUUID);

    /**
     * 修改UUID的状态为启用
     * @param id
     */
    @Update(" update uuid set isEnable=1 where id = #{id}")
    void updateUUIDStatus(Long id);

    /**
     * 查找使用次数最少的免费的UUID
     * @return
     */
    @Select("SELECT * FROM uuid  where isVip =0  and  vmessID = #{vmessID} ORDER BY useTimes  LIMIT 1")
    UUID findFreeUUID(Long vmessID);
    /**
     * 查找未使用的VIP的UUID
     * @return
     */
    @Select("SELECT * FROM uuid  where isVip =1  and  vmessID = #{vmessID} ORDER BY useTimes  LIMIT 1")
    UUID findVIPUUID(Long vmessID);

    /**
     * 查找用户的VIP UUID
     * @param userID
     * @return
     */
    @Select("SELECT * FROM user_uuid  where isVip =1 AND userID=#{userID}")
    UserUUID findVIPUUIDByUserID(Long userID);

    /**
     * 查找用户免费 UUID
     * @param userID
     * @return
     */
    @Select("SELECT * FROM user_uuid  where isVip =0 AND userID=#{userID}")
    UserUUID findFreeUUIDByUserID(Long userID);

    /**
     * 修改UUID的使用次数
     * @param id
     */
    @Update("update `uuid` set `useTimes`= `useTimes`+1 WHERE id = #{id}")
    void updateUUIDTimes(Long id);

    /**
     * 用户过期修改状态
     * @param uid
     */
    @Update("update user_uuid set delFlag= 1  WHERE userID = #{uid} and isVip = 1")
    void deleteUserUUID(Long uid);
}
