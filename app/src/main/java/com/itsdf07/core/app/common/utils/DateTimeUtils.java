package com.itsdf07.core.app.common.utils;

import android.text.TextUtils;

import java.io.File;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Description: 时间格式、转换 工具类
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/11
 */
public class DateTimeUtils {

    /**
     * 11: yyyy/MM/dd HH:mm:ss <br>
     * 12: yyyy-MM-dd HH:mm:ss<br>
     * 13: yyyy年MM月dd日 HH:mm:ss<br>
     * <p>
     * 21: yyyy/MM/dd HH:mm <br>
     * 22: yyyy-MM-dd HH:mm<br>
     * 23: yyyy年MM月dd日 HH:mm<br>
     *
     * @param format
     * @return 返回时间格式
     */
    public static SimpleDateFormat dateTimeFormat(int format) {
        SimpleDateFormat df;
        switch (format) {
            case 11:
                df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                break;
            case 12:
                df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
            case 13:
                df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                break;

            case 21:
                df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                break;
            case 22:
                df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                break;
            case 23:
                df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                break;

            case 31:
                df = new SimpleDateFormat("yyyy/MM/dd");
                break;
            case 32:
                df = new SimpleDateFormat("yyyy-MM-dd");
                break;
            case 33:
                df = new SimpleDateFormat("yyyy年MM月dd日");
                break;

            case 41:
                df = new SimpleDateFormat("yyyy/MM");
                break;
            case 42:
                df = new SimpleDateFormat("yyyy-MM");
                break;
            case 43:
                df = new SimpleDateFormat("yyyy年MM月");
                break;

            case 51:
                df = new SimpleDateFormat("MM/dd");
                break;
            case 52:
                df = new SimpleDateFormat("MM-dd");
                break;
            case 53:
                df = new SimpleDateFormat("MM月dd日");
                break;

            case 61:
                df = new SimpleDateFormat("MM/dd HH:mm");
                break;
            case 62:
                df = new SimpleDateFormat("MM-dd HH:mm");
                break;
            case 63:
                df = new SimpleDateFormat("MM月dd日 HH:mm");
                break;

            default:
                df = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
                break;
        }
        return df;
    }

    /**
     * 根据时间格式化时间
     *
     * @param dataTime 时间
     * @param style    样式
     * @return 格式化时间
     */
    public static String formatDateTime(Date dataTime, int style) {
        SimpleDateFormat df = dateTimeFormat(style);
        return df.format(dataTime);
    }

    /**
     * 根据时间格式化时间<br>
     *
     * @param time  时间
     * @param style 样式
     * @return 格式化时间
     */
    public static String formatDateTime(long time, int style) {
        return formatDateTime(new Date(time), style);
    }

    /**
     * 返回当前指定时间格式化的时间<br>
     *
     * @param style 样式
     * @return 格式化时间
     */
    public static String formatCurrentDateTime(int style) {
        return formatDateTime(new Date(), style);
    }


    /**
     * 时间戳转成提示性日期格式（今天、昨天、昨天之后的具体日期)
     *
     * @param milSecond
     * @return
     */
    public static String getDateToString(long milSecond, int style) {
        //现在时间
        String now = dateTimeFormat(style).format(milSecond);
        //昨天
        Calendar ycal = Calendar.getInstance();
        ycal.add(Calendar.DATE, -1);
        String yday = dateTimeFormat(style).format(ycal.getTime());
        //今天
        Calendar tcal = Calendar.getInstance();
        tcal.add(Calendar.DATE, 0);
        String today = dateTimeFormat(style).format(tcal.getTime());
        if (now.equals(today)) {
            return "今天";
        } else if (now.equals(yday)) {
            return "昨天";
        } else {
            return formatDateTime(milSecond, style);//其他时间段
        }
    }



    /**
     * 计算两个日期相隔的天数.
     *
     * @param d1
     * @param d2
     * @return 返回两个日期相隔的天数, 如果是同一天返回0.
     */
    public static int getDaysBetween(Calendar d1, Calendar d2) {
        if (d1.after(d2)) {
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                d1.add(Calendar.YEAR, 1);
            }
            while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    public static String formatMsTime(long lt) {
        Date msgTime = new Date(lt);

        return msgTime.toString().substring(11, 19);
    }

    public static String formatMsShortTime(long lt) {
        Date msgTime = new Date(lt);

        return msgTime.toString().substring(11, 16);
    }

    public static String formatMsDate(long lt) {
        Date msgTime = new Date(lt);

        int year = msgTime.getYear() + 1900;
        int month = msgTime.getMonth();
        int day = msgTime.getDate();

        return year + "-" + (month + 1) + "-" + (day);
    }

    public static String formatMsDate(long lt, String separator) {
        Date msgTime = new Date(lt);

        int year = msgTime.getYear() + 1900;
        int month = msgTime.getMonth();
        int day = msgTime.getDate();

        return year + separator + (month + 1) + separator + (day);
    }

    /**
     * 根据文件路径
     * 返回文件修改时间
     *
     * @param path 文件路径
     * @return 文件修改时间
     */
    public static long getFileDate(String path) {
        File file = new File(path);
        return file.lastModified();
    }

    /**
     * 将2013:10:08 11:48:07如此格式的时间
     * 转化为毫秒数
     *
     * @param datetime 字符串时间
     * @return 毫秒数
     */
    public static long dateTimeToMS(String datetime) {
        if (TextUtils.isEmpty(datetime)) return 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        final Date parse;
        try {
            parse = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        return parse.getTime();
    }

    /**
     * 将2013:10:08 11:48:07如此格式的时间
     * 转化为毫秒数
     *
     * @param datetime 字符串时间
     * @return 毫秒数
     */
    public static long dateTimeToMS(String datetime, int style) {
        if (TextUtils.isEmpty(datetime)) return 0;
        SimpleDateFormat sdf = null;
        switch (style) {
            case 0:
                sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                break;
            case 1:
                sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                break;
            case 2:
                sdf = new SimpleDateFormat("yyyy年MM月");
                break;
        }
        final Date parse;
        try {
            parse = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        return parse.getTime();
    }

    /**
     * 将2013:10:08 11:48:07如此格式的时间
     * 转化为毫秒数
     *
     * @param datetime 字符串时间
     * @return 毫秒数
     */
    public static long dateTimeToMSs(String datetime) {
        if (TextUtils.isEmpty(datetime)) return 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        final Date parse;
        try {
            parse = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        return parse.getTime();
    }

    /**
     * 将20131008114807如此格式的时间
     * 转化为毫秒数
     *
     * @param datetime 字符串时间
     * @return 毫秒数
     */
    public static long dateTime2MS(String datetime) {
        if (TextUtils.isEmpty(datetime) || datetime.length() != 14) {
            return 0;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(datetime.substring(0, 4));
        stringBuilder.append(":");
        stringBuilder.append(datetime.substring(4, 6));
        stringBuilder.append(":");
        stringBuilder.append(datetime.substring(6, 8));
        stringBuilder.append(" ");
        stringBuilder.append(datetime.substring(8, 10));
        stringBuilder.append(":");
        stringBuilder.append(datetime.substring(10, 12));
        stringBuilder.append(":");
        stringBuilder.append(datetime.substring(12, 14));
        return dateTimeToMS(stringBuilder.toString(), 0);
    }


    public static Calendar getNextTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal;
    }

    /**
     * 将毫秒转化为时分秒，用于播放录音显示时长
     *
     * @param timeLength
     * @return
     */
    public static String ssToTime(long timeLength) {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
//        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String hms = formatter.format(timeLength);
        return hms;
    }

    /**
     * 方法名：getNowTime()
     * 功    能：获取当前年月日
     * 参    数：无
     * 返回值：String
     */
    public static String getNowTime() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"); //当前年月日 时分秒
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    /**
     * 方法名：getNowLongTime()
     * 功    能：获取当前系统时间戳
     * 参    数：无
     * 返回值：long
     */
    public static long getNowLongTime() {
        return System.currentTimeMillis();
    }

    /**
     * 方法名：getNowLongDateTime()
     * 功    能：获取当前时间戳(只精确到天)
     * 参    数：无
     * 返回值：long
     */
    public static long getNowLongDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String format = simpleDateFormat.format(date);

        Date parse = null;
        try {
            parse = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse.getTime();
    }

    /**
     * 方法名：getCurrentMinute()
     * 功    能：获取当前时间的总分钟数
     * 参    数：无
     * 返回值：int
     */
    public static int getCurrentMinute() {
        Calendar cal = Calendar.getInstance();// 当前日期
        int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时
        int minute = cal.get(Calendar.MINUTE);// 获取分钟
        int minuteOfDay = hour * 60 + minute;// 从0:00分开是到目前为止的分钟数
        return minuteOfDay;
    }


    /**
     * 方法名：getDate(long time)
     * 功    能：获取到指定时间的long值（精确到天）
     * 参    数：long time
     * 返回值：long
     */
    public static long getDate(long time) {
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);

        Date parse = null;
        try {
            parse = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse.getTime();
    }

    /**
     * 方法名：getIncreaseTime(int days)
     * 功    能：获取增加指定天数后系统的时间long
     * 参    数：int days
     * 返回值：long
     */
    public static long getIncreaseTime(int days) {
        Calendar c = Calendar.getInstance();//日期
        c.add(Calendar.DAY_OF_MONTH, days);//日期增加到指定天数后
        long time = c.getTime().getTime();
        long date = getDate(time);
        return date;
    }

    /**
     * 方法名： timeInterval(long startTime, long endTime)
     * 功    能：获取两个时间点，间隔多少分钟
     * 参    数：daysBetween(Date startDate, Date endDate)
     * 返回值：int
     */
    public static int timeInterval(long startTime, long endTime) {
        Date startDate = new Date(startTime);
        Date endDate = new Date(endTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            startDate = sdf.parse(sdf.format(startDate));
            endDate = sdf.parse(sdf.format(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        long time1 = calendar.getTimeInMillis();


        calendar.setTime(endDate);
        long time2 = calendar.getTimeInMillis();


        long betweenDays = (time2 - time1) / 1000 / 60;
        return Integer.parseInt(String.valueOf(betweenDays));

    }

    /**
     * 方法名：judgingTime(int startMinutes, int endMinutes)
     * 功    能：判断当前总分钟对应的时间是否处于两个总分钟数对应的时间点之间
     * 参    数：int startMinutes, int endMinutes
     * 返回值：boolean
     */
    public static boolean judgingTime(int startMinutes, int endMinutes) {
        int currentMinute = getCurrentMinute();//获取当前时间的总分钟数
        if (currentMinute >= startMinutes && currentMinute <= endMinutes) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 方法名：getTodayAppointTime(String time)
     * 功    能：获取今日指定时间的时间戳
     * 参    数：String time
     * 返回值：long
     */
    public static long getTodayAppointTime(String time) {
        String dateTime = getNowTime() + " " + time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition position = new ParsePosition(0);
        Date date = simpleDateFormat.parse(dateTime, position);
        return date.getTime();
    }

    public static String getCrmTimeData(String date) {
        if (TextUtils.isEmpty(date)) {
            return "";
        }
        long time = Long.valueOf(date);
        String today = formatDateTime(System.currentTimeMillis(), 9);
        String timeStr = formatDateTime(time, 9);
        if (today.split(" ")[0].equals(timeStr.split(" ")[0])) {
            return "今天 " + timeStr.split(" ")[1];
        } else {
            return timeStr.replace("/", "月").replaceFirst(" ", "日 ");
        }
    }

    public static String getCrmData(String date) {
        if (TextUtils.isEmpty(date)) {
            return "";
        }
        long time = Long.valueOf(date);
        String timeStr = formatDateTime(time, 13);
        return timeStr;
    }

    public static String getCrmTimeFlag(String date) {
        long time = Long.valueOf(date);
        String timeStr = formatDateTime(time, 12);
        //时间戳转成提示性日期格式（昨天、今天……)
        String dateToString = getDateToString(Long.valueOf(date), 14);
        if (dateToString.equals("今天")) {
            return dateToString;
        } else if (dateToString.equals("昨天")) {
            return dateToString;
        } else {
            return timeStr;
        }
    }


    public static String getCrmDataTime(String date) {
        if (TextUtils.isEmpty(date)) {
            return "";
        }
        long time = Long.valueOf(date);
        String today = formatDateTime(System.currentTimeMillis(), 9);
        String timeStr = formatDateTime(time, 9);
        if (today.split(" ")[0].equals(timeStr.split(" ")[0])) {
            return "今天";
        } else {
            return timeStr.replace("/", "月").replaceFirst(" ", "日 ");
        }
    }

    public static String timeCovert(String timeLengthStr) {
        if (TextUtils.isEmpty(timeLengthStr)) {
            return "";
        }
        try {
            int timeLength = Integer.valueOf(timeLengthStr);
            if (timeLength >= 60) {
                return (int) (timeLength / 60) + "分" + (timeLength % 60) + "秒";
            } else if (timeLength > 0 && timeLength < 60) {
                return timeLength + "秒";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return "";
    }
}
