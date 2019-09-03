package com.dragon.fruit.controller;

import cn.jiguang.common.utils.StringUtils;
import cn.jsms.api.SendSMSResult;
import com.dragon.fruit.common.constant.UserConstant;
import com.dragon.fruit.common.utils.HttpServletUtil;
import com.dragon.fruit.common.utils.SMSUtils;
import com.dragon.fruit.entity.po.user.CheckCode;
import com.dragon.fruit.entity.po.user.User;
import com.dragon.fruit.service.ICheckCodeService;
import com.dragon.fruit.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.MediaTypes;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Api(tags = "用户操作接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ICheckCodeService checkCodeService;

    @Autowired
    private IUserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 发送手机短信验证码
     *
     * @param phone
     * @return
     */
    @ApiOperation(value = "发送手机短信验证码")
    @ResponseBody
    @RequestMapping(value = "/sendCode", method = RequestMethod.GET)
    public Map sendCode(  String phone, HttpServletRequest request) {
        Map resultMap = new HashMap();
        if (StringUtils.isEmpty(phone) || phone == null) {
            resultMap.put("code", -1);
            resultMap.put("message", "请输入手机号！");
            return resultMap;
        }
        //获取IP 地址
        String IP = HttpServletUtil.getRealRemoteAddr(request);

        String messageID = stringRedisTemplate.opsForValue().get(UserConstant.REGISTER_PREFIX + phone);
        if (messageID != null || !StringUtils.isEmpty(messageID)) {
            resultMap.put("code", 501);
            resultMap.put("message", "您验证法发送太频繁，请一分钟后再发送！！");
            return resultMap;
        }

        int times = 0;
        if (stringRedisTemplate.hasKey(UserConstant.REGISTER_COUNT_PREFIX + phone)) {
            times = Integer.parseInt(stringRedisTemplate.opsForValue().get(UserConstant.REGISTER_COUNT_PREFIX + phone));
        }

        if (times >= UserConstant.MESSAGE_MAX_SEND_TIMES) {
            resultMap.put("code", 502);
            resultMap.put("message", "您当前手机号今天已发送五次，超过每天最高发送次数！！");
            return resultMap;
        }


        //发送手机验证码
        SendSMSResult result = SMSUtils.sendSMSCode(phone);
        if (null == result) {
            resultMap.put("code", 503);
            resultMap.put("message", "系统可发送短信条数已用完，请联系客服或管理员！！");
            return resultMap;
        }
        //把手机号和发送验证码的messageId存入Redis 并且设置过期时间为60秒
        stringRedisTemplate.opsForValue().set(UserConstant.REGISTER_PREFIX + phone, result.getMessageId(), 60, TimeUnit.SECONDS);

        //把当前手机号发送验证码次数存入redis  每发一次计数加一  每天24点清除
        stringRedisTemplate.boundValueOps(UserConstant.REGISTER_COUNT_PREFIX + phone).increment(1);

        Long expireTime = getExpireTime();

        stringRedisTemplate.expire(UserConstant.REGISTER_COUNT_PREFIX + phone, expireTime, TimeUnit.MINUTES);

        checkCodeService.saveCheckCode(phone, IP, result);


        resultMap.put("code", result.getResponseCode());
        resultMap.put("message", result.getOriginalContent());
        return resultMap;
    }


    /**
     * 校验验证码
     *
     * @param phone
     * @param checkCode
     * @return
     */
    @ApiOperation(value = "校验手机短信验证码")
    @ResponseBody
    @RequestMapping(value = "/checkCode", method = RequestMethod.POST)
    public Map checkVerificationCode(
                                     @RequestParam(name = "phone") String phone,
                                     @RequestParam(name = "checkCode") String checkCode) {

        //根据手机号查询验证码的msgID
        CheckCode checkCodeEntity = checkCodeService.findCheckCodeBYPhone(phone);
        String msgID = checkCodeEntity.getMsgId();

        Map resultMap = SMSUtils.validSMSCode(checkCode, msgID);

        return resultMap;
    }


    @ApiOperation(value = "注册")
    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Map register(
            @RequestHeader(required = false, value = "token") String token, @RequestBody User user) {


        String result = userService.saveRegisterUser(user);
        //
        Map resultMap = new HashMap();


        if (result.equalsIgnoreCase("ok")) {
            resultMap.put("code", 200);
            resultMap.put("message", "success");
        } else {
            resultMap.put("code", 500);
            resultMap.put("message", result);
            if (result.equalsIgnoreCase("noCode")) {
                resultMap.put("code", -1);
                resultMap.put("message", "验证码输入有误");
            }
        }
        return resultMap;
    }

    /**
     * 用户登录
     *
     * @return
     */
    @ApiOperation(value = "用户登录")
    @ResponseBody
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public Map userLogin(
                         @RequestBody User user) {

        Map<String, Object> map = userService.login(user);

        return map;
    }


    /**
     * 用户登出
     *
     * @return
     */
    @ApiOperation(value = "用户登出")
    @ResponseBody
    @RequestMapping(value = "/userLoginOut", method = RequestMethod.GET)
    public Map userLoginOut(
            @RequestParam(name = "id") String id) {

        Map<String, Object> map = userService.loginOut(id);

        return map;
    }

    /**
     * 生成用户邀请码
     *
     * @return
     */
    @ApiOperation(value = "生成用户邀请码")
    @ResponseBody
    @RequestMapping(value = "/generateUserAskCode", method = RequestMethod.GET)
    public Map generateUserAskCode(
                                   @RequestParam(name = "userID") Long userID) {

        Map<String, Object> map = userService.generateUserAskCode(userID);

        return map;
    }


    /**
     * 修改用户昵称
     *
     * @return
     */
    @ApiOperation(value = "修改用户昵称")
    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public Map updateUser(
                          @RequestBody User user) {
        Map<String, Object> map = userService.updateUser(user);

        return map;
    }

    /**
     * 根据用户ID获取用户的基本信息
     *
     * @return
     */
    @ApiOperation(value = "获取用户信息")
    @ResponseBody
    @RequestMapping(value = "/userDetail", method = RequestMethod.GET)
    public Map userDetail(@RequestParam(name = "userID") Long userID) {
        Map<String, Object> map = userService.userDetail(userID);

        return map;
    }


    //获取要设置的过期时间
    private Long getExpireTime() {
        //计算当前时间到凌晨的时间
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime today_start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime tommorrow_start = today_start.plusDays(1);
        Duration between = Duration.between(now, tommorrow_start);
        return between.toMinutes();

    }
}
