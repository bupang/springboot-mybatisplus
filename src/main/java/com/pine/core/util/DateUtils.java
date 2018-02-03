package com.pine.core.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenbupang on 2017-11-24 9:33
 */
public class DateUtils {
    private final static String DEFAULT_FORMATER = "yyyy-MM-dd HH:mm:ss";

    public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public final static String YYYYMMDD_ZH = "yyyy年MM月dd日 HH:mm:ss";
    public final static String YYYYMMDD_SPRIT = "yyyy/MM/dd HH:mm:ss";

    /**
     * 获取当前时间字符串
     * @return
     * @throws Exception
     */
    public static String getCurrentDateStr() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMATER);
        return sdf.format(getCurrentDate());
    }

    /**
     * 获取当前时间字符串
     * @param formater
     * @return
     * @throws Exception
     */
    public static String getCurrentDateStr(String formater) throws Exception{
        if (StringUtils.isBlank(formater)) {
            formater = DEFAULT_FORMATER;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        return sdf.format(getCurrentDate());
    }

    /**
     * 获取当前时间字符串
     * @param current
     * @param formater
     * @return
     * @throws Exception
     */
    public static String getCurrentDateStr(Date current, String formater) throws Exception {
        return getDateStr(current, formater);
    }

    /**
     * 获取时间字符串
     * @param date
     * @param formater
     * @return
     * @throws Exception
     */
    public static String getDateStr(Date date, String formater) throws Exception {
        if(date == null){
            return null;
        }
        if (StringUtils.isBlank(formater)) {
            formater = DEFAULT_FORMATER;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        return sdf.format(date);
    }

    /**
     * 获取当前时间
     * @return
     * @throws Exception
     */
    public static Date getCurrentDate() throws Exception{
        return new Date();
    }

    /**
     * 获取当前时间戳
     */
    public static long getCurrentDateTime(){
        Date currentDate = new Date();
        return currentDate.getTime();
    }

    public static void main(String[] args) throws Exception{
        System.out.print(getCurrentDateStr(YYYYMMDD_SPRIT));
    }
}