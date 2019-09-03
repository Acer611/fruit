package com.dragon.fruit.common.utils;


import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @see  UUID的工具类
 * @author  Gaofei
 * @Date 2018-10-18
 */
public class UUIDUtil {
    public static String getUuidStr(){
        String uuids = UUID.randomUUID().toString();
        return uuids;
    }


    /**
     * 生成订单号
     * @return
     */
    public static String getOrderID(){
        //随机生成一位整数
        int random = (int) (Math.random()*9+1);
        String valueOf = String.valueOf(random);
        //生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();
        //可能为负数
        if(hashCode<0){
            hashCode = -hashCode;
        }
        String value = valueOf + String.format("%015d", hashCode);
        return value;
    }

    public static String getOrderIDWithTime(){
        SimpleDateFormat sfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String strDate = sfDate.format(new Date());
        //得到17位时间如：20170411094039080
        System.out.println("时间17位：" + strDate);
        //为了防止高并发重复,再获取3个随机数
        String random = getRandom620(3);

        //最后得到20位订单编号。
        return strDate + random;
    }


    /**
     * 获取6-10 的随机位数数字
     * @param length	想要生成的长度
     * @return result
     */
    public static String getRandom620(Integer length) {
        String result = "";
        Random rand = new Random();
        int n = 20;
        if (null != length && length > 0) {
            n = length;
        }
        int randInt = 0;
        for (int i = 0; i < n; i++) {
            randInt = rand.nextInt(10);
            result += randInt;
        }
        return result;
    }

    public static void main(String[] args) {

        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,1);
        LocalDate date = LocalDate.now();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        LocalDateTime dateTime = LocalDateTime.now();
        int result = dateTime.get(weekFields.weekOfYear());
        int result1 = date.get(weekFields.weekOfYear());
        int offset = date.getDayOfWeek().getValue();


        System.out.println("orderID ：" + getOrderID());
        System.out.println("orderID ：" + getOrderIDWithTime());

    }
}
