package com.dragon.fruit.service.impl;

import com.alibaba.fastjson.JSON;
import com.dragon.fruit.common.constant.UserConstant;
import com.dragon.fruit.common.utils.*;
import com.dragon.fruit.dao.fruit.CheckCodeDao;
import com.dragon.fruit.dao.fruit.UserDao;
import com.dragon.fruit.dao.fruit.VmessDao;
import com.dragon.fruit.dao.uuid.UUIDDao;
import com.dragon.fruit.dao.uuid.UserLoginLogDao;
import com.dragon.fruit.entity.po.user.*;
import com.dragon.fruit.entity.po.uuid.UUID;
import com.dragon.fruit.entity.po.uuid.UserUUID;
import com.dragon.fruit.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service(value="userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userInfoMapper;

    @Autowired
    CheckCodeDao checkCodeMapper;

    @Autowired
    private UUIDDao uuidMapper;

    @Autowired
    private VmessDao vmessMappr;
    @Autowired
    private UserLoginLogDao userLoginLogMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String saveRegisterUser(User user) {


        //检查手机号是否被注册
        User userInfo = userInfoMapper.findUserByPhone(user.getPhone());
        if(null!=userInfo&&userInfo.getPhone()!=null){
            return "该手机号已被注册~";
        }


        // 校验验证码是否匹配
        String checkCode =  user.getCheckCod();
        //根据手机号查询验证码的msgID
        CheckCode checkCodeEntity = checkCodeMapper.findCheckCodeBYPhone(user.getPhone());
        if(checkCodeEntity == null){
            return "noCode";
        }
        String msgID = checkCodeEntity.getMsgId();


        Map resultMap =  SMSUtils.validSMSCode(checkCode,msgID);

        if(Integer.parseInt(resultMap.get("code").toString())!=200){
            return resultMap.get("message").toString();
        }


        //加密密码
        String password = MD5.MD5Encode(user.getPassword());
        //根据邀请码查找用户信息
        User fatherUser = userInfoMapper.findUserByAskCode(user.getAskCode());

        user.setPassword(password);
        user.setStatus(0);
        user.setIsVip(0L);
        user.setCreateTime(new Date());
        if(null!=fatherUser){
            user.setPid(fatherUser.getId());
        }

        //向数据库插入记录
        userInfoMapper.insertUserInfo(user);

        // 邀请人的时长增加一天
        if(null!=fatherUser){
            Date expireDate = fatherUser.getExpiredDate();
            if(expireDate == null){
                expireDate = new Date();
            }
            LocalDateTime expiredDate8 = DateUtils.dateToLocalDateTime(expireDate);
            LocalDateTime newExpiredDate8 = expiredDate8.plusDays(1);
            Date newExpiredDate = DateUtils.localDateTimeToDate(newExpiredDate8);
            fatherUser.setExpiredDate(newExpiredDate);
            // 修改邀请人的时长
            userInfoMapper.updateUser(fatherUser);

        }

        //修改验证码状态为已用
        updateCheckCode(user);

        //分配线路 和UUID
        saveVmessInfo(user.getPhone());
        
        return "ok";
    }

    /**
     * 保存线路信息
     * @param phone
     */
    private void saveVmessInfo(String phone) {
        //用户信息
        User user = userInfoMapper.findUserByPhone(phone);

        // 分配线路
         //获取要分配的免费线路
        Vmess vemess = vmessMappr.findVmess();
        /*UserVmess userVmess = new UserVmess();
        userVmess.setUserID(user.getId());
        userVmess.setVmessID(vemess.getId());
        userVmess.setCreateDate(new Date());
        vmessMappr.saveUserVmess(userVmess);*/


        //分配UUID
        UserUUID userUUID = new UserUUID();
        // 查找当前使用次数最少的免费的UUID
        UUID uuid = uuidMapper.findFreeUUID(vemess.getId());
        userUUID.setUserID(user.getId());
        userUUID.setUuid(uuid.getUuid());
        userUUID.setIsVip(0);
        userUUID.setUid(uuid.getId());
        userUUID.setCreateDate(new Date());
        userUUID.setVmessID(vemess.getId());
        // 保存UUID和用户的关联关系
        uuidMapper.saveUserUUID(userUUID);


        //修改当前线路已分配人数
        vmessMappr.updateUserPeople(vemess.getId());

        // 修改UUID 的使用次数
        uuidMapper.updateUUIDTimes(uuid.getId());




    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public Map<String, Object> login(User user) {

        Map<String,Object> map = new HashMap<>();
        //获取密码 并MD5加密后和数据库比对
        String password = user.getPassword();
        password = MD5.MD5Encode(password);
        user.setPassword(password);



        // 根据手机号和密码获取用户信息
        User userInfo = userInfoMapper.login(user);


        if(null==userInfo){
            map.put("code","502");
            map.put("message","账号密码错误~");

            return map;
        }
        //  记录用户登录日志
        recordUserLoginLog(userInfo);
        //新登录用户踢出老登录用户的信息
        String oldToken = stringRedisTemplate.opsForValue().get("User_GetTokenByUser:" + userInfo.getId());
        stringRedisTemplate.delete("User_GetTokenByUser:" + userInfo.getId());
        stringRedisTemplate.delete("User_GetUserByToken:" + oldToken);


        //生成根据用户信息token （生成规则 MD5(ip+phone+当前时间)）
        String token = MD5.MD5Encode(userInfo.getId()+userInfo.getPhone()+ new Date());
        //String userInfoStr = JSON.toJSON(userInfo).toString();
        stringRedisTemplate.opsForValue().set("User_GetUserByToken:" + token,  userInfo.getId()+"", 4, TimeUnit.HOURS);
        stringRedisTemplate.opsForValue().set("User_GetTokenByUser:" + userInfo.getId(),  token, 4, TimeUnit.HOURS);


        // 处理null 为 “”
        userInfo = handlerUserInfo(userInfo);
        map.put("code","200");
        map.put("message","success");
        map.put("user",userInfo);
        map.put("token",token);

        return map;
    }

    /**
     * 记录用户登录日志
     * @param user
     */
    private void recordUserLoginLog(User user) {
        UserLoginLog userLoginLog = new UserLoginLog();
        userLoginLog.setCreateTime(new Date());
        userLoginLog.setLoginTime(new Date());
        userLoginLog.setUserID(user.getId());
        userLoginLog.setUserNickName(user.getNickName());
        userLoginLog.setDriveCode("");
        userLoginLog.setSystem("");
        userLoginLogMapper.saveUserLoginLog(userLoginLog);
    }


    /**
     * 生成用户邀请码
     * @param userID
     * @return
     */
    @Override
    public Map<String, Object> generateUserAskCode(Long userID) {
        Map<String,Object> map = new HashMap<>();
        User  user = userInfoMapper.findUserByID(userID);
        if(user.getAskCode()==null){
            String askCode = ShareCode.toSerialCode(userID);
            user.setAskCode(askCode);
            //邀请码保存入库
            userInfoMapper.generateAskCode(user);
        }

        String askURL = generateAskURL(user);
        String qrURL = generateQRURL(user);


        map.put("code","200");
        map.put("message","success");
        map.put("askCode",user.getAskCode());
        map.put("askURL",askURL);
        map.put("qrURL",qrURL);
        return map;
    }

    /**
     * 生成邀请二维码
     * @param user
     * @return
     */
    private String generateQRURL(User user) {
        String askURL = generateAskURL(user);
        String askCode = user.getAskCode();
        try{
            //判断系统
            String os = System.getProperty("os.name");
            if (os != null && os.toLowerCase().indexOf("win") > -1) {
                QrCodeCreateUtil.createQrCode(new FileOutputStream(new File("D:\\askCode\\" + askCode +".jpg")),askURL,900,"JPEG");
            } else {
                QrCodeCreateUtil.createQrCode(new FileOutputStream(new File("/opt/images/" + askCode +".jpg")),askURL,900,"JPEG");

            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return  "/askCode/" + askCode +".jpg";

    }

    /**
     * 生成邀请链接
     * @param user
     * @return
     */
    private String generateAskURL(User user) {
        return  UserConstant.ASK_URL + user.getAskCode();
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    public Map<String, Object> updateUser(User user) {

        Map<String,Object> map = new HashMap<>();
        userInfoMapper.updateUser(user);
        map.put("code","200");
        map.put("message","success");
        return map;
    }

    /**
     * 获取用户基本信息
     * @param userID
     * @return
     */
    @Override
    public Map<String, Object> userDetail(Long userID) {
        Map<String,Object> map = new HashMap<>();
        User user = userInfoMapper.findUserByID(userID);
        user = handlerUserInfo(user);
        map.put("code","200");
        map.put("message","success");
        map.put("data",user);
        return map;
    }

    /**
     * 定时任务 定时修改过期用户的状态
     */
    @Override
    public void updateExpiredUserStatus() {
        //获取过期用户列表
        List<User> expiredUserList = userInfoMapper.findExpiredUser();
        for (User user:expiredUserList) {
            user.setIsVip(0L);
            userInfoMapper.updateUser(user);
            //删除 分配线路信息
            removeVmess(user.getId());

        }

    }

    /**
     * 用户登出
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> loginOut(String id) {
        Map<String,Object> map = new HashMap<>();
        String oldToken = stringRedisTemplate.opsForValue().get("User_GetTokenByUser:" + id);
        stringRedisTemplate.delete("User_GetTokenByUser:" + id);
        stringRedisTemplate.delete("User_GetUserByToken:" + oldToken);
        map.put("code","200");
        map.put("message","success");
        return map;
    }

    /**
     * 修改状态为删除
     * @param uid
     */
    private void removeVmess(Long uid) {
        uuidMapper.deleteUserUUID(uid);
    }

    private void updateCheckCode(User user) {
        CheckCode checkCode = new CheckCode();
        checkCode.setIsUse(1);
        checkCode.setUseAt(LocalDateTime.now());
        checkCode.setPhone(user.getPhone());
        checkCodeMapper.updateCheckCode(checkCode);
    }

    /**
     * 处理null 为 ""
     * @param userInfo
     * @return
     */
    private User handlerUserInfo(User userInfo) {
        userInfo.setAskCode(userInfo.getAskCode()==null?"":userInfo.getAskCode());
        userInfo.setCheckCod(userInfo.getCheckCod()==null?"":userInfo.getCheckCod());
        userInfo.setPhone(userInfo.getPhone()==null?"":userInfo.getPhone());
        userInfo.setPassword(userInfo.getPassword()==null?"":userInfo.getPassword());
        userInfo.setNickName(userInfo.getNickName()==null?"":userInfo.getNickName());
        userInfo.setImageUrl(userInfo.getImageUrl()==null?"":userInfo.getImageUrl());
        userInfo.setDriveCode(userInfo.getDriveCode()==null?"":userInfo.getDriveCode());
        userInfo.setIsVip(userInfo.getIsVip()==null?0:userInfo.getIsVip());
        userInfo.setStatus(userInfo.getStatus()==null?0:userInfo.getStatus());
        userInfo.setCreateTime(userInfo.getCreateTime()==null?new Date():userInfo.getCreateTime());
        userInfo.setTcID(userInfo.getTcID()==null?0:userInfo.getTcID());
        userInfo.setTcName(userInfo.getTcName()==null?"":userInfo.getTcName());
        return userInfo;
    }
}
