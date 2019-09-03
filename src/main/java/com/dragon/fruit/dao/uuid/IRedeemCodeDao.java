package com.dragon.fruit.dao.uuid;

import com.dragon.fruit.entity.po.uuid.RedeemCode;
import com.dragon.fruit.entity.po.uuid.RedeemCodeUserLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 兑换码数据处理层接口
 */
@Mapper
public interface IRedeemCodeDao {
    /**
     * 生成兑换码
     * @param redeemCode
     */
    @Insert(" insert into redeem_code(code,expireDate,isUsed,type) " +
            " values(#{code},#{expireDate},#{isUsed},#{type})")
    void save(RedeemCode redeemCode);

    /**
     * 根据code 查询兑换码信息
     * @param code
     * @return
     */
    @Select("select * from `redeem_code` where code = #{code}")
    RedeemCode findByCode(String code);

    /**
     * 修改兑换码状态
     * @param code
     */
    @Update("update `redeem_code` set isUsed =1 where code = #{code} ")
    void updateRedeemCodeStatusByCode(String code);

    /**
     * 保存兑换码使用记录
     * @param redeemCodeUserLog
     */
    @Insert(" insert into `redeemcodeuser_log`(userID,redeemCode,useTime) " +
            " values(#{userID},#{redeemCode},#{useTime})")
    void saveRedeemCodeUserLog(RedeemCodeUserLog redeemCodeUserLog);
}
