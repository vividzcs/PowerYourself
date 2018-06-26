package com.nwuer.core.common.util;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * 获取Cron表达式(注:?表示不确定,用于日和星期)
 * 没有的自己写
 * @author vividzc
 * @date 2018/6/15 12:52
 */
public class CronExpressionUtil {

    private static Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));


    /**
     * 所有字段都自定义
     * 秒 分 时 日 月 周 年
     */
    public static String getCronExpressionSpecialDay(int second,int minute,int hour
            ,int day,int month,int week,int year){
        StringBuilder sb = new StringBuilder();
        sb.append(second);
        sb.append(" ");
        sb.append(minute);
        sb.append(" ");
        sb.append(hour);
        sb.append(" ");
        sb.append(day);
        sb.append(" ");
        sb.append(month);
        sb.append(" ?");
        sb.append(" ");
        sb.append(year);
        return sb.toString();
    }

    public static String getCronExpressionSpecialWeek(int second,int minute,int hour
            ,int day,int month,int week,int year){
        StringBuilder sb = new StringBuilder();
        sb.append(second);
        sb.append(" ");
        sb.append(minute);
        sb.append(" ");
        sb.append(hour);
        sb.append(" ?");
        sb.append(" ");
        sb.append(month);
        sb.append(" ");
        sb.append(week);
        sb.append(" ");
        sb.append(year);
        return sb.toString();
    }

    public static String getCronExpression(int second,int minute,int hour
            ,int day,int month){
        StringBuilder sb = new StringBuilder();
        sb.append(second);
        sb.append(" ");
        sb.append(minute);
        sb.append(" ");
        sb.append(hour);
        sb.append(" ");
        sb.append(day);
        sb.append(" ");
        sb.append(month);
        sb.append(" ? *");
        return sb.toString();
    }

    public static String getCronExpression(int second,int minute,int hour
            ,int day){
        StringBuilder sb = new StringBuilder();
        sb.append(second);
        sb.append(" ");
        sb.append(minute);
        sb.append(" ");
        sb.append(hour);
        sb.append(" ");
        sb.append(day);
        sb.append(" * ? *");
        return sb.toString();
    }



    public static String getCronExpression(int second,int minute,int hour){
        StringBuilder sb = new StringBuilder();
        sb.append(second);
        sb.append(" ");
        sb.append(minute);
        sb.append(" ");
        sb.append(hour);
        sb.append(" * * ? *");
        return sb.toString();
    }

    public static String getCronExpression(int second,int minute){
        StringBuilder sb = new StringBuilder();
        sb.append(second);
        sb.append(" ");
        sb.append(minute);
        sb.append(" * * * ? *");
        return sb.toString();
    }

    public static String getCronExpression(int second){
        StringBuilder sb = new StringBuilder();
        sb.append(second);
        sb.append(" * * * * ? *");
        return sb.toString();
    }


    public static String getCronExpressionOnce(int second,int minute,int hour
            ,int day,int month,int week){
        int year = calendar.get(1);
        StringBuilder sb = new StringBuilder();
        sb.append(second);
        sb.append(" ");
        sb.append(minute);
        sb.append(" ");
        sb.append(hour);
        sb.append(" ");
        sb.append(day);
        sb.append(" ");
        sb.append(month);
        sb.append(" ");
        sb.append(week);
        sb.append(" ");
        sb.append(year);
        return sb.toString();
    }

    public static String getCronExpressionOnce(int second,int minute,int hour
            ,int day,int month){
        int year = calendar.get(1);
        int week = calendar.get(7)-1;
        StringBuilder sb = new StringBuilder();
        sb.append(second);
        sb.append(" ");
        sb.append(minute);
        sb.append(" ");
        sb.append(hour);
        sb.append(" ");
        sb.append(day);
        sb.append(" ");
        sb.append(month);
        sb.append(" ");
        sb.append(week);
        sb.append(" ");
        sb.append(year);
        return sb.toString();
    }
    public static String getCronExpressionOnce(int second,int minute,int hour
            ,int day){
        int year = calendar.get(1);
        int week = calendar.get(7)-1;
        int month = calendar.get(2)+1;
        StringBuilder sb = new StringBuilder();
        sb.append(second);
        sb.append(" ");
        sb.append(minute);
        sb.append(" ");
        sb.append(hour);
        sb.append(" ");
        sb.append(day);
        sb.append(" ");
        sb.append(month);
        sb.append(" ");
        sb.append(week);
        sb.append(" ");
        sb.append(year);
        return sb.toString();
    }

    public static String getCronExpressionOnce(int second,int minute,int hour){
        int year = calendar.get(1);
        int week = calendar.get(7)-1;
        int month = calendar.get(2)+1;
        int day = calendar.get(5);
        StringBuilder sb = new StringBuilder();
        sb.append(second);
        sb.append(" ");
        sb.append(minute);
        sb.append(" ");
        sb.append(hour);
        sb.append(" ");
        sb.append(day);
        sb.append(" ");
        sb.append(month);
        sb.append(" ");
        sb.append(week);
        sb.append(" ");
        sb.append(year);
        return sb.toString();
    }

    public static String getCronExpressionOnce(int second,int minute){
        int year = calendar.get(1);
        int week = calendar.get(7)-1;
        int month = calendar.get(2)+1;
        int day = calendar.get(5);
        int hour = calendar.get(11);
        StringBuilder sb = new StringBuilder();
        sb.append(second);
        sb.append(" ");
        sb.append(minute);
        sb.append(" ");
        sb.append(hour);
        sb.append(" ");
        sb.append(day);
        sb.append(" ");
        sb.append(month);
        sb.append(" ");
        sb.append(week);
        sb.append(" ");
        sb.append(year);
        return sb.toString();
    }

    public static String getCronExpressionOnce(int second){
        int year = calendar.get(1);
        int week = calendar.get(7)-1;
        int month = calendar.get(2)+1;
        int day = calendar.get(5);
        int hour = calendar.get(11);
        int minute = calendar.get(12);
        StringBuilder sb = new StringBuilder();
        sb.append(second);
        sb.append(" ");
        sb.append(minute);
        sb.append(" ");
        sb.append(hour);
        sb.append(" ");
        sb.append(day);
        sb.append(" ");
        sb.append(month);
        sb.append(" ");
        sb.append(week);
        sb.append(" ");
        sb.append(year);
        return sb.toString();
    }
}