package com.dragon.fruit.service.impl;

import com.dragon.fruit.service.ISpiderJSService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @program fruit
 * @description:
 * @author: Gaofei
 * @create: 2019-08-19
 */

@Service(value = "spiderJSService")
public class SpiderJSServiceImple implements ISpiderJSService {

    private static final Logger logger = LoggerFactory.getLogger(SpiderJSServiceImple.class);



    /**
     *  爬取急速系列数据
     */
    @Override
    public void spiderJSvlue() {
        StringBuffer buffer = new StringBuffer();
        try{

//      String urlpath="https://sou.zhaopin.com/?jl=801&kw=java&kt=3";
            String urlpath="https://www.fastlottery.sg/pc/#/post/1431";
            Document doc = Jsoup.connect(urlpath).get();

            URL url = new URL(urlpath);
            URLConnection conn = url.openConnection();

            InputStream in =conn.getInputStream();
            //字节流-》字符流 InputStreamReader
            InputStreamReader reader = new InputStreamReader(in,"utf-8");
            //按行读
            BufferedReader breader = new BufferedReader(reader);
            //读
            String line = "";
            while((line= breader.readLine())!=null)
            {
                buffer.append(line);
            }

        }catch (Exception e){

        }
        System.out.println( "-------------------------------"+buffer+"");
    }
}
