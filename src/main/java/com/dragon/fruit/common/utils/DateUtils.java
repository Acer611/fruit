package com.dragon.fruit.common.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;

/**
 * @description: 日期工具类
 * @author: Gaofei
 * @create: 2018/10/18 11:10
 */

public class DateUtils {


    //缺省日期格式
    public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";
    //全日期格式
    public static final String DATE_FULL_FORMAT = "yyyy-MM-dd HH:mm:ss";
    //全日期格式无秒
    public static final String DATE_NO_SS_FORMAT = "yyyy-MM-dd HH:mm";


    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds   精确到秒的字符串
     * @return
     */
    public static String timeStamp2Date(String seconds) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }



        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FULL_FORMAT);
        String date = sdf.format(new Date(Long.parseLong(seconds)));
        return date;
    }


    /**
     * CST 格式时间转化为正常时间格式
     * @param cstDate
     * @return 北京时间格式
     */
    public static  Date CST2Date(String cstDate){

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

        try{
            Date d = sdf.parse(cstDate);
            String formatDate = new SimpleDateFormat(DATE_FULL_FORMAT).format(d);

            return  stringToDate(formatDate);

        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }


    /**
     * 字符串时间格式转化为日期格式
     * @param dateStr
     * @return
     */
    public static Date stringToDate(String dateStr){

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FULL_FORMAT);

        Date date = null;
        try {
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;

    }

    /**
     * 将 Date 转换成LocalDateTime
     * atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }



    /**
     * 将LocalDateTime 转换成 Date
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }


    public static void main(String[] args) {
       String l = "1528777561147";

        String date = timeStamp2Date(l);
        System.out.println("....."+date);


    }

}
