package com.nwuer.core.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 将前端传来的字符串转化成日期的数组,方便定时任务设置
 * @author vividzc
 * @date 2018/6/15 22:55
 */
public class DateParseForCronExpressionUtil {

    private final static String STANDARD_FORMAT = "yyyy/MM/dd HH:mm";
    /**
     * DateParseForCronExpressionUtil.parse("2019/04/22 22:12")
     * @param time
     * @return 0-6 : 秒 分 时 日 月 星期 年
     * @throws ParseException
     */
    public static Integer[] parse(String time) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(STANDARD_FORMAT);
        Date date = simpleDateFormat.parse(time);
        calendar.setTime(date);
        return new Integer[]{
                calendar.get(13),
                calendar.get(12),
                calendar.get(11),
                calendar.get(5),
                calendar.get(2)+1,
                calendar.get(7),
                calendar.get(1)
        };
    }

    /*public static LocalDateTime getLocalDateTime(String time) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(STANDARD_FORMAT);
        return LocalDateTime.parse(time,dateTimeFormatter);
    }*/

    public static Date getDate(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(STANDARD_FORMAT);
        return simpleDateFormat.parse(time);
    }
}
