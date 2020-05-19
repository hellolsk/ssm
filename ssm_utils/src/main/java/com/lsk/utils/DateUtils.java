package com.lsk.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:${六月的雨}
 * @Date:2020/2/6 15:48
 * @Description:ssm com.lsk.utils
 */
public class DateUtils {

    //日期转换为指定字符串格式
    public static String dateToString(Date date,String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(date);
        return format;
    }

    //字符串格式转换为指定日期
    public static Date stringToDate(String date,String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date parse = simpleDateFormat.parse(date);
        return parse;
    }
}
