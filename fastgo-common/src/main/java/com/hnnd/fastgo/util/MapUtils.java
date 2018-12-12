package com.hnnd.fastgo.util;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * map工具类
 * Created by Administrator on 2018/12/12.
 */
public class MapUtils {

    /**
     * 从map中获取key
     * @param key
     * @return
     */
    public static String getString(Map<String,Object> map,String key){
        if(isEmptyMap(map) || StringUtils.isEmpty(key)) {
            return "";
        }else {
            Object value = map.containsKey(key)?map.get(key).toString():"";
            return value==null?"":value.toString();
        }
    }

    public static Integer getInteger(Map<String,Object> map,String key) {
        String value = getString(map,key);
        return value!=null?Integer.parseInt(value):null;
    }

    private static boolean isEmptyMap(Map<String,Object> map) {
        if(null==map|| map.isEmpty()) {
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        Map<String,Object> testMap = new HashMap<>();
        testMap.put("a",null);
        testMap.put("b","");
        testMap.put("c","C");
        testMap.put("d",1);
        System.out.println(getInteger(testMap,"d"));
    }
}
