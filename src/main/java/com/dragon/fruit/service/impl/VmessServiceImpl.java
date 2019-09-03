package com.dragon.fruit.service.impl;

import com.dragon.fruit.dao.fruit.UserDao;
import com.dragon.fruit.dao.fruit.VmessDao;
import com.dragon.fruit.dao.uuid.UUIDDao;
import com.dragon.fruit.entity.po.user.User;
import com.dragon.fruit.entity.po.user.UserVmess;
import com.dragon.fruit.entity.po.user.Vmess;
import com.dragon.fruit.entity.po.uuid.UserUUID;
import com.dragon.fruit.service.IVmessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service(value="vmessService")
public class VmessServiceImpl implements IVmessService {

    @Autowired
    private UserDao userInfoMapper;

    @Autowired
    private VmessDao vmessMapper;
    @Autowired
    private UUIDDao uuidMapper;

    /**
     * 查找当前用户下的可用线路
     * @param userID
     * @return
     */
    @Override
    public Map<String, Object> listVmess(Long userID) {
        Map<String,Object> map = new HashMap<>();
        User user = userInfoMapper.findUserByID(userID);
        if(user == null){
            map.put("code","-1");
            map.put("message","查无此人，请检查用户ID是否正确！");
            return map;
        }

        //查找用户可用的的线路列表
        List<Vmess> vmessList = vmessMapper.findVmessByUserID(userID);

        //List<Map> resultList = new ArrayList<>();
        List<Map> resutVmessList = new ArrayList<>();
        for (Vmess vmess:vmessList) {
            //Map jsonMap = generatentJsonConfig(vmess);
            //resultList.add(jsonMap);
            Map resultMap = handlerVmess(vmess);
            resutVmessList.add(resultMap);
        }



        //map.put("vip",vipList);
        map.put("vmess",resutVmessList);
        map.put("code","200");
        map.put("message","success");
        return map;
    }




    /**
     * 保存用户当前的线路
     * @param userID
     * @param vmessID
     * @return
     */
    @Override
    public Map<String, Object> saveUserVmess(Long userID, Long vmessID) {

        Map<String,Object> map = new HashMap<>();

        UserVmess userVmess = new UserVmess();
        userVmess = vmessMapper.findUserVmessByUserID(userID);
        if(null == userVmess){
            userVmess = new UserVmess();
            userVmess.setUserID(userID);
            userVmess.setVmessID(vmessID);
            vmessMapper.saveUserVmess(userVmess);
        }else{
            userVmess.setUserID(userID);
            userVmess.setVmessID(vmessID);
            vmessMapper.updateUserVmess(userVmess);

        }


        map.put("code","200");
        map.put("message","success");
        return map;
    }

    /**
     * 线路数据处理  把 null  处理为""
     * @param vmess
     * @return
     */
    private Map handlerVmess(Vmess vmess) {
        Map resultMap = new HashMap();

        resultMap.put("name",vmess.getName()==null?"":vmess.getName());
        resultMap.put("address",vmess.getAddress()==null?"":vmess.getAddress());
        resultMap.put("port",vmess.getPort()==null?"":vmess.getPort());
        switch(vmess.getNetwork()){
            case 1:
                resultMap.put("network","tcp");
                break;
            case 2:
                resultMap.put("network","kcp");
                break;
            case 3:
                resultMap.put("network","ws");
                break;
            case 4:
                resultMap.put("network","h2");
                break;
            default:
                resultMap.put("network","ws");
                break;
        }
        resultMap.put("alterId",vmess.getAlterId()==null?0:vmess.getAlterId());

        //加密方式 0 none 1 auto 2 aes-128-cfb 3 aes-128-gcm 4 chacha20-poly1305
        switch(vmess.getSecurity()){
            case 0:
                resultMap.put("security","none");
                break;
            case 1:
                resultMap.put("security","auto");
                break;
            case 2:
                resultMap.put("security","aes-128-cfb");
                break;
            case 3:
                resultMap.put("security","aes-128-gcm");
                break;
            case 4:
                resultMap.put("security","chacha20-poly1305");
                break;
            default:
                resultMap.put("security","chacha20-poly1305");;
                break;
        }

        resultMap.put("remark",vmess.getRemarks()==null?"":vmess.getRemarks());

        resultMap.put("type",vmess.getType()==null?"":vmess.getType());

        resultMap.put("host",vmess.getHost()==null?"":vmess.getHost());
        resultMap.put("path",vmess.getPath()==null?"":vmess.getPath());
        resultMap.put("hsts",vmess.getHsts()==null?"":vmess.getHsts());
        if(vmess.getAllowInsecure()==null){
            resultMap.put("allowInsecure", true);
        }else{
            if(vmess.getAllowInsecure()==0){
                resultMap.put("allowInsecure", false);
            }else{
                resultMap.put("allowInsecure", true);
            }
        }

        resultMap.put("isVip",vmess.getIsVip()==null?0:vmess.getIsVip());
        resultMap.put("uuid",vmess.getUUID()==null?"":vmess.getUUID());
        resultMap.put("configVersion",vmess.getConfigVersion()==null?"":vmess.getConfigVersion());

        return resultMap;
    }


    private Map generatentJsonConfig(Vmess vmess) {
        Map baseMap = new HashMap();
        baseMap.put("country",vmess.getCountry());
        baseMap.put("icon",vmess.getIcon());
        Map dataMap = new HashMap();

        /**log */
        Map logMap = new HashMap();
        logMap.put("error","");
        logMap.put("loglevel","info");
        logMap.put("access","");
        dataMap.put("log",logMap);

        /** inbounds */
        List<Map> inboundsMapList = new ArrayList<>();
        Map inbounds1Map = new HashMap();
        inbounds1Map.put("listen","127.0.0.1");
        inbounds1Map.put("protocol","socks");
        inbounds1Map.put("port","1080");
        Map inbounds1settings1Map = new HashMap();
        inbounds1settings1Map.put("ip","");
        inbounds1settings1Map.put("userLevel",0);
        inbounds1settings1Map.put("timeout",360);
        inbounds1settings1Map.put("udp",false);
        inbounds1settings1Map.put("auth","noauth");

        inbounds1Map.put("settings",inbounds1settings1Map);
        inboundsMapList.add(inbounds1Map);

        Map inbounds2Map = new HashMap();
        inbounds2Map.put("listen","127.0.0.1");
        inbounds2Map.put("protocol","http");
        inbounds2Map.put("port","1087");
        Map inbounds2settings1Map = new HashMap();
        inbounds2settings1Map.put("timeout",360);

        inbounds2Map.put("settings",inbounds2settings1Map);
        inboundsMapList.add(inbounds2Map);
        dataMap.put("inbounds",inboundsMapList);


        /** outbounds */
        List<Map> outboundsMapList = new ArrayList<>();
        Map outbounds1Map = new HashMap();

        Map muxMap = new HashMap();
        muxMap.put("enabled",false);
        muxMap.put("concurrency",8);
        outbounds1Map.put("mux",muxMap);

        outbounds1Map.put("protocol","vmess");

        Map streamSettingsMap = new HashMap();
        Map wsSettingsMap = new HashMap();
        wsSettingsMap.put("path",vmess.getPath());
        Map outbounds1HeaderMap = new HashMap();
        outbounds1HeaderMap.put("host","");
        wsSettingsMap.put("headers",outbounds1HeaderMap);
        streamSettingsMap.put("wsSettings",wsSettingsMap);


        Map tlsSettingsMap = new HashMap();
        tlsSettingsMap.put("allowInsecure",true);
        streamSettingsMap.put("tlsSettings",tlsSettingsMap);

        //传输协议 1 tcp 2 kcp 3 ws 4 h2
        streamSettingsMap.put("security",vmess.getHsts());

        switch(vmess.getNetwork()){
            case 1:
                streamSettingsMap.put("network","tcp");
                break;
            case 2:
                streamSettingsMap.put("network","kcp");
                break;
            case 3:
                streamSettingsMap.put("network","ws");
                break;
            case 4:
                streamSettingsMap.put("network","h2");
                break;
            default:
                streamSettingsMap.put("network","ws");
                break;
        }
        outbounds1Map.put("streamSettings",streamSettingsMap);
        outbounds1Map.put("tag","agentout");


        Map outbounds1SettingMap = new HashMap();
        List<Map> vnextList = new ArrayList<>();

        outbounds1SettingMap.put("vnext",vnextList);
        Map vnextMap = new HashMap();
        vnextMap.put("address","www.apple-email.net");
        List<Map> userList = new ArrayList<>();
        Map userMap = new HashMap();
        userMap.put("id",vmess.getUUID());
        userMap.put("alterId",vmess.getAlterId());
        userMap.put("level",0);

        //加密方式 0 none 1 auto 2 aes-128-cfb 3 aes-128-gcm 4 chacha20-poly1305
        switch(vmess.getSecurity()){
            case 0:
                userMap.put("security","none");
                break;
            case 1:
                userMap.put("security","auto");
                break;
            case 2:
                userMap.put("security","aes-128-cfb");
                break;
            case 3:
                userMap.put("security","aes-128-gcm");
                break;
            case 4:
                userMap.put("security","chacha20-poly1305");
                break;
            default:
                userMap.put("security","chacha20-poly1305");;
                break;
        }

        userList.add(userMap);
        vnextMap.put("users",userList);
        vnextMap.put("port",443);
        vnextList.add(vnextMap);
        outbounds1Map.put("settings",outbounds1SettingMap);
        outboundsMapList.add(outbounds1Map);

        Map outbounds2Map = new HashMap();
        outbounds2Map.put("tag","direct");
        outbounds2Map.put("freedom","direct");
        Map outbounds2SettingMap = new HashMap();
        outbounds2SettingMap.put("domainStrategy","AsIs");
        outbounds2SettingMap.put("redirect","");
        outbounds2SettingMap.put("userLevel",0);
        outbounds2Map.put("settings",outbounds2SettingMap);
        outboundsMapList.add(outbounds2Map);


        Map outbounds3Map = new HashMap();

        outbounds3Map.put("tag","blockout");
        outbounds3Map.put("protocol","blackhole");
        Map outboundsSetting3Map = new HashMap();
        Map responseMap = new HashMap();
        responseMap.put("type","none");
        outboundsSetting3Map.put("response",responseMap);
        outbounds3Map.put("settings",outboundsSetting3Map);

        outboundsMapList.add(outbounds3Map);
        dataMap.put("outbounds",outboundsMapList);



        /** dns */
        Map dnssMap = new HashMap();
        dnssMap.put("servers",new ArrayList<>());
        dataMap.put("dns",dnssMap);


        /** routing */
        Map routingMap = new HashMap();
        routingMap.put("strategy","rules");
        Map routingSettingMap = new HashMap();
        routingSettingMap.put("domainStrategy","IPIfNonMatch");
        List<Map> ruleList = new ArrayList<>();
        Map ruleMap = new HashMap();
        ruleMap.put("outboundTag","direct");
        ruleMap.put("type","field");
        List ipList = new ArrayList();
        ipList.add("geoip:cn");
        ipList.add("geoip:private");
        ruleMap.put("ip", ipList);
        List domainList = new ArrayList();
        domainList.add("geosite:cn");
        domainList.add("geosite:speedtest");
        ruleMap.put("domain", domainList);
        ruleList.add(ruleMap);
        routingSettingMap.put("rules",ruleList);
        routingMap.put("settings",routingSettingMap);
        dataMap.put("routing",routingMap);



        /** transport */
        Map transportMap = new HashMap();

        dataMap.put("transport",transportMap);


        baseMap.put("data",dataMap);
        return  baseMap;
    }


}
