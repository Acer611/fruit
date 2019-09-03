package com.dragon.fruit.config;

import com.avos.avoscloud.AVOSCloud;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @see  项目初始化时，初始化连接leanCloud的配置
 * @Order 注解的执行优先级是按value值从小到大顺序。
 * @author Gaofei
 * @date 2018-10-17
 *
 */

/*@Component
@Order(1)
public class LeanCloudConfig  implements ApplicationRunner {

    @Value("${spring.leancloud.appId}")
    private String appId;

    @Value("${spring.leancloud.appKey}")
    public  String appKey;
    //短信发送的key
    @Value("${spring.leancloud.masterKey}")
    public  String masterKey ;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMasterKey() {
        return masterKey;
    }

    public void setMasterKey(String masterKey) {
        this.masterKey = masterKey;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //AVOSCloud.initialize("35VUIFIUreWdAlTOQ4OlQwqE-gzGzoHsz", "0qfyOTjPj0b0Nanrve1AqyB9", "GJ2JtzNxJr5Klnjwd4ej7g1S");
        AVOSCloud.initialize(appId, appKey, masterKey);
        AVOSCloud.setDebugLogEnabled(true);

    }
}*/
