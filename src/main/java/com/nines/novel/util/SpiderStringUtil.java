package com.nines.novel.util;

/**
 * @ClassName: StringUtil
 * @Description: 字符串工具类
 * @author: Nines
 * @date: 2020年03月31日 18:18
 */
public final class SpiderStringUtil {

    private SpiderStringUtil() {
    }

    /**
     * 分割字符串 返回后面部分
     * @param str 目标字符串
     * @param regex 分割字符串
     * @return String
     */
    public static String splitLast(String str, String regex){
        int index = str.indexOf(regex);
        if (index < 0 || index == str.length()){
            return str;
        }
        return str.substring(index+1);
    }

    /**
     * 分割字符串 返回后面部分
     * @param str 目标字符串
     * @param regex 分割字符串
     * @return String
     */
    public static String splitFirst(String str, String regex){
        int index = str.indexOf(regex);
        if (index < 0 || index == str.length()){
            return str;
        }
        return str.substring(0, index);
    }

}
