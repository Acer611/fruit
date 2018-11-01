package com.dragon.fruit.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program fruit
 * @description: 打散List集合的排序
 * @author: Gaofei
 * @create: 2018/11/01 11:12
 */

public class ListRandomUtils {


    public static List randomList(List taragetList){
       List resultList = new ArrayList();

       Map map = new HashMap();
       while(map.size()<taragetList.size()){

           int random = (int) (Math.random() * taragetList.size());
           if (!map.containsKey(random)) {
               map.put(random,"");
               resultList.add(taragetList.get(random));
           }
       }

       return  resultList;
    }
}
