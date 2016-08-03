package com.violetlife.weixin.pay.sdk.util;

/**
 * 描述:com.violetlife.weixin.pay.sdk.util
 * 作者:zhaoruilong
 * 时间:2016/8/3 10:34
 */
public class StringUtils {
    public static boolean isEmpty(final CharSequence source){
        return source==null||source.length()==0;
    }


    public static boolean isNotEmpty(final CharSequence source){
        return !isEmpty(source);
    }

    public static boolean isBlank(final CharSequence source){
        int strLen;
        if(source==null||(strLen=source.length())==0)
        {
            return true;
        }

        for(int i=0;i<strLen;i++){
            if(!Character.isWhitespace(source.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(final CharSequence source){
          return !isBlank(source);
    }

    public static String convertNullToEmpty(Object source){
        return   source==null?"":source.toString();
    }
}
