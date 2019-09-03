package com.dragon.fruit.dao.fruit;


import com.dragon.fruit.entity.po.user.UserVmess;
import com.dragon.fruit.entity.po.user.Vmess;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface VmessDao {
    /**
     * 查找vip线路
     * @return
     */
    @Select("select * from vmess where isVip =1")
    public List<Vmess> listVip() ;

    /**
     * 查找普通线路
     * @return
     */
    @Select("select * from vmess where isVip =0")
    List<Vmess> listVmess();

    /**
     * 保存当前用户的线路
     * @param userVmess
     */
    @Insert(" insert into user_vmess(vmessID,userID,createDate) " +
            " values(#{vmessID},#{userID},#{createDate})")
    void saveUserVmess(UserVmess userVmess);

    /**
     * 查找当前用户选择的线路
     * @param userID
     * @return
     */
    @Select("select * from user_vmess where userID = #{userID}")
    UserVmess findUserVmessByUserID(Long userID);

    /**
     * 修改用户的线路ID
     * @param userVmess
     * @return
     */
    @Update(" update user_vmess set vmessID=#{vmessID} where userID = #{userID}")
    void updateUserVmess(UserVmess userVmess);

    /**
     * 获取用户要分配免费的线路 （分配数额未满 切已分配最大的线路）
     * @return
     */
    @Select("SELECT * FROM vmess WHERE isVip=0 and currentConnection<maxConnections ORDER BY currentConnection  DESC LIMIT 1")
    Vmess findVmess();

    /**
     * 修改当前线路已分配人数（+1）
     * @param id
     */

    @Update("update `vmess` set `currentConnection`= `currentConnection`+1 WHERE id = #{id}")
    void updateUserPeople(Long id);

    /**
     * 获取用户要分配VIP的线路 （分配数额未满 切已分配最大的线路）
     * @return
     */
    @Select("SELECT * FROM vmess WHERE isVip=1 and currentConnection<maxConnections ORDER BY currentConnection  DESC LIMIT 1")
    Vmess findVIPVmess();

    /**
     * 查找用的可用的线路ID
     * @param userID
     * @return
     */
    @Select("SELECT uu.uuid  as UUID, vm.* FROM vmess vm  LEFT JOIN user_uuid uu on uu.vmessID =vm.id WHERE uu.userID= #{userID} AND uu.delFlag =0")
    List<Vmess> findVmessByUserID(Long userID);
}
