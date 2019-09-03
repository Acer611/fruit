# fruit  
###项目介绍  
VPN果实项目    
###软件架构  

Build --- Gradle  
Compiler ---JAVA 1.8

后台技术：

```
SpringBoot2  
HikariCP   
Mybatis       
swagger2   
Scheduled    
spring aop    
```

1. 使用**拦截器** ，拦截所有/api/*的的请求， 对请求头中没有token,和token过期的请求进行拦截（token有效期两个小时）  

2. 使用**定时任务**， 目前在跑定时任务分两种，第一种为每分钟跑一次，目的是检查token表中的过期token,并标识为删除  

   第二种为每天凌晨两点跑一次，删除token表中的过期已经标识为删除的token,防止token表数据增量累计过大

3. 使用**AOP** （方便以后对所有的请求做记录用，根据以后需求可完善功能）

4.   使用**多线程**，异步记录文章推荐记录，频道访问记录，文章阅读记录

5. 使用**logback**配置记录，日志，日志分天分级别记录（每天一个日志文件夹）  

Token 生成规则 MD5 加密三个字段（固定的Key+随机字符串+当前时间）生成




\#使用说明

服务启动后，可通过访问 host:prot/swagger-ui.html(如：<http://localhost:9999/swagger-ui.html> )查看API 接口
  
 调用接口可用POSTMAN ,先获取token ，然后请求头中加入token

