package com.dragon.fruit.common.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
     * UTC 时间转换为北京时间
     *
     * @param UTCStr
     * @throws ParseException
     */
    public static Date UTCToCST(String UTCStr) throws ParseException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FULL_FORMAT);
        date = sdf.parse(UTCStr);
        System.out.println("UTC时间: " + date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
        //calendar.getTime() 返回的是Date类型，也可以使用calendar.getTimeInMillis()获取时间戳
        System.out.println("北京时间: " + calendar.getTime());
        return calendar.getTime();
    }


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
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }


    /**
     * CST 格式时间转化为正常时间格式
     * @param cstDate
     * @return 北京时间格式
     */
    public static  String CST2Date(String cstDate){

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

        try{
            Date d = sdf.parse(cstDate);
            String formatDate = new SimpleDateFormat(DATE_FULL_FORMAT).format(d);
            return  formatDate;

        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }


    public static void main(String[] args) {
        String DateStr = "Tue Oct 09 13:40:17 CST 2018";

        String date = "Thu Aug 27 18:05:49 CST 2015";

        System.out.println(CST2Date(date));


    }

}
