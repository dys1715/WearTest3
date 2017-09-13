package daemon.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 */
@SuppressLint("SimpleDateFormat")
public class TimeUtils {

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_HM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_HM_NO_DATE = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_HM_NO_HMS = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 时间日期格式化到年月日.
     */
    public static final String dateFormatYMD = "yyyy-MM-dd";
    /**
     * 时间日期格式化到年月.
     */
    public static final String dateFormatYM = "yyyy-MM";
    /**
     * 时分.
     */
    public static final String dateFormatHM = "HH:mm";
    /**
     * 时间日期格式化到年月日时分秒.
     */
    public static final String dateFormatYMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将long型时间, 转换成 String型时间
     *
     * @param timeInMillis 距1970.1.1-0:0:0时间间隔
     * @param dateFormat   "yyyy年MM月dd日 HH:mm:ss"
     * @return String 型时间
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * 将long型时间, 转换成 String型时间, 时间格式[yyyy-MM-dd HH:mm:ss]
     *
     * @param timeInMillis 距1970.1.1-0:0:0时间间隔
     * @return String 型时间
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * 把string类型的时间转换成毫秒
     *
     * @param timeStr 时间，如2017-8-4 14:36
     * @param sdf     传入的时间格式
     * @return 毫秒表示的时间
     */
    public static long timeConvertToMillis(String timeStr, SimpleDateFormat sdf) {
        try {
            return sdf.parse(timeStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取两个时间的时间差
     *
     * @param timeStr1 时间1
     * @param timeStr2 时间2
     * @param sdf      传入的时间格式
     * @return 毫秒级时间差
     */
    public static long getTimeInterval(String timeStr1, String timeStr2, SimpleDateFormat sdf) {
        long time1 = timeConvertToMillis(timeStr1, sdf);
        long time2 = timeConvertToMillis(timeStr2, sdf);
        return time1 - time2;
    }

    /***
     * 获得当前毫秒级时间
     *
     * @return 距离0时间, 的毫秒级时间间隔
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /***
     * 获得当前毫秒级时间
     *
     * @return 距离0时间, 的毫秒级时间间隔
     */
    public static String getCurrentTimeMillisInString() {
        return System.currentTimeMillis() + "";
    }

    /**
     * 获得当前毫秒级时间
     *
     * @return 距离0时间, 的毫秒级时间间隔
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * 获得当前时间,
     *
     * @param dateFormat 时间格式 如[yyyy-MM-dd HH:mm:ss.SSSZ]
     * @return String 型时间
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
        //将long时间转换成 String型时间（获得当前毫秒级时间）
    }

    /**
     * 描述：计算两个日期所差的天数.
     *
     * @param milliseconds1 the milliseconds1
     * @param milliseconds2 the milliseconds2
     * @return int 所差的天数
     */
    public static int getOffectDay(long milliseconds1, long milliseconds2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(milliseconds1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(milliseconds2);
        //先判断是否同年
        int y1 = calendar1.get(Calendar.YEAR);
        int y2 = calendar2.get(Calendar.YEAR);
        int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int maxDays = 0;
        int day = 0;
        if (y1 - y2 > 0) {
            maxDays = calendar2.getActualMaximum(Calendar.DAY_OF_YEAR);
            day = d1 - d2 + maxDays;
        } else if (y1 - y2 < 0) {
            maxDays = calendar1.getActualMaximum(Calendar.DAY_OF_YEAR);
            day = d1 - d2 - maxDays;
        } else {
            day = d1 - d2;
        }
        return day;
    }

    /**
     * 描述：计算两个日期所差的小时数.
     *
     * @param date1 第一个时间的毫秒表示
     * @param date2 第二个时间的毫秒表示
     * @return int 所差的小时数
     */
    public static int getOffectHour(long date1, long date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        int h1 = calendar1.get(Calendar.HOUR_OF_DAY);
        int h2 = calendar2.get(Calendar.HOUR_OF_DAY);
        int h = 0;
        int day = getOffectDay(date1, date2);
        h = h1 - h2 + day * 24;
        return h;
    }

    /**
     * 描述：计算两个日期所差的分钟数.
     *
     * @param date1 第一个时间的毫秒表示
     * @param date2 第二个时间的毫秒表示
     * @return int 所差的分钟数
     */
    public static int getOffectMinutes(long date1, long date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        int m1 = calendar1.get(Calendar.MINUTE);
        int m2 = calendar2.get(Calendar.MINUTE);
        int h = getOffectHour(date1, date2);
        int m = 0;
        m = m1 - m2 + h * 60;
        return m;
    }

    /**
     * 描述：获取本周一.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getFirstDayOfWeek(String format) {
        return getDayOfWeek(format, Calendar.MONDAY);
    }

    /**
     * 描述：获取本周的某一天.
     *
     * @param format        the format
     * @param calendarField the calendar field
     * @return String String类型日期时间
     */
    private static String getDayOfWeek(String format, int calendarField) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            int week = c.get(Calendar.DAY_OF_WEEK);
            if (week == calendarField) {
                strDate = mSimpleDateFormat.format(c.getTime());
            } else {
                int offectDay = calendarField - week;
                if (calendarField == Calendar.SUNDAY) {
                    offectDay = 7 - Math.abs(offectDay);
                }
                c.add(Calendar.DATE, offectDay);
                strDate = mSimpleDateFormat.format(c.getTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 描述：获取本月第一天.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getFirstDayOfMonth(String format) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            //当前月的第一天
            c.set(GregorianCalendar.DAY_OF_MONTH, 1);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 描述：获取本月最后一天.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getLastDayOfMonth(String format) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            // 当前月的最后一天
            c.set(Calendar.DATE, 1);
            c.roll(Calendar.DATE, -1);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 描述：获取表示当前日期的0点时间毫秒数.
     *
     * @return the first time of day
     */
    public static long getFirstTimeOfDay() {
        Date date = null;
        try {
            String currentDate = getCurrentDate(dateFormatYMD);
            date = getDateByFormat(currentDate + " 00:00:00", dateFormatYMDHMS);
            return date.getTime();
        } catch (Exception e) {
        }
        return -1;
    }

    /**
     * 描述：获取表示当前日期时间的字符串.
     *
     * @param format 格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String String类型的当前日期时间
     */
    public static String getCurrentDate(String format) {
        String curDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            Calendar c = new GregorianCalendar();
            curDateTime = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curDateTime;
    }


    /**
     * 描述：获取表示当前日期的0点时间毫秒数.
     *
     * @return the first time of day
     */
    public static long getFirstTimeOfDay(String currentDate) {
        try {
            Date date = getDateByFormat(currentDate + " 00:00:00", dateFormatYMDHMS);
            return date.getTime();
        } catch (Exception e) {
        }
        return -1;
    }

    /**
     * 描述：String类型的日期时间转化为Date类型.
     *
     * @param strDate String形式的日期时间
     * @param format  格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return Date Date类型日期时间
     */
    public static Date getDateByFormat(String strDate, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = mSimpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 描述：获取表示当前日期24点时间毫秒数.
     *
     * @return the last time of day
     */
    public static long getLastTimeOfDay() {
        Date date = null;
        try {
            String currentDate = getCurrentDate(dateFormatYMD);
            date = getDateByFormat(currentDate + " 24:00:00", dateFormatYMDHMS);
            return date.getTime();
        } catch (Exception e) {
        }
        return -1;
    }

    /**
     * 描述：判断是否是闰年()
     * <p>(year能被4整除 并且 不能被100整除) 或者 year能被400整除,则该年为闰年.
     *
     * @param year 年代（如2012）
     * @return boolean 是否为闰年
     */
    public static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 400 != 0) || year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 描述：根据时间返回格式化后的时间的描述.
     * 小于1小时显示多少分钟前  大于1小时显示今天＋实际日期，大于今天全部显示实际时间
     *
     * @param strDate   the str date
     * @param outFormat the out format
     * @return the string
     */
    public static String formatDateStr2Desc(String strDate, String outFormat) {

        DateFormat df = new SimpleDateFormat(dateFormatYMDHMS);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c2.setTime(df.parse(strDate));
            c1.setTime(new Date());
            int d = getOffectDay(c1.getTimeInMillis(), c2.getTimeInMillis());
            if (d == 0) {
                int h = getOffectHour(c1.getTimeInMillis(), c2.getTimeInMillis());
                if (h > 0) {
                    return "今天" + getStringByFormat(strDate, dateFormatHM);
                    //return h + "小时前";
                } else if (h < 0) {
                    //return Math.abs(h) + "小时后";
                } else if (h == 0) {
                    int m = getOffectMinutes(c1.getTimeInMillis(), c2.getTimeInMillis());
                    if (m > 0) {
                        return m + "分钟前";
                    } else if (m < 0) {
                        //return Math.abs(m) + "分钟后";
                    } else {
                        return "刚刚";
                    }
                }

            } else if (d > 0) {
                if (d == 1) {
                    //return "昨天"+getStringByFormat(strDate,outFormat);
                } else if (d == 2) {
                    //return "前天"+getStringByFormat(strDate,outFormat);
                }
            } else if (d < 0) {
                if (d == -1) {
                    //return "明天"+getStringByFormat(strDate,outFormat);
                } else if (d == -2) {
                    //return "后天"+getStringByFormat(strDate,outFormat);
                } else {
                    //return Math.abs(d) + "天后"+getStringByFormat(strDate,outFormat);
                }
            }

            String out = getStringByFormat(strDate, outFormat);
            if (!TextUtils.isEmpty(out)) {
                return out;
            }
        } catch (Exception e) {
        }

        return strDate;
    }

    /**
     * 描述：Date类型转化为String类型.
     *
     * @param date   the date
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getStringByFormat(Date date, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
        String strDate = null;
        try {
            strDate = mSimpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 描述：获取指定日期时间的字符串,用于导出想要的格式.
     *
     * @param strDate String形式的日期时间，必须为yyyy-MM-dd HH:mm:ss格式
     * @param format  输出格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String 转换后的String类型的日期时间
     */
    public static String getStringByFormat(String strDate, String format) {
        String mDateTime = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(dateFormatYMDHMS);
            c.setTime(mSimpleDateFormat.parse(strDate));
            SimpleDateFormat mSimpleDateFormat2 = new SimpleDateFormat(format);
            mDateTime = mSimpleDateFormat2.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDateTime;
    }

    /**
     * 描述：判断指定日期是否小于当前日期
     *
     * @param year  int类型，表示指定日期的年份
     * @param month int类型，表示指定日期的月份  (从0算起)
     * @param date  int类型，表示指定日期的日
     */
    public static boolean isLessDate(int year, int month, int date) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -5);
        return year < c.get(Calendar.YEAR)
                || (year == c.get(Calendar.YEAR) && (month) < c.get(Calendar.MONTH))
                || (year == c.get(Calendar.YEAR) && (month) <= c.get(Calendar.MONTH) && date < c.get(Calendar.DATE));

    }

    /**
     * 判断指定日期是否在限定的时间区间内
     *
     * @param year     int类型，表示指定日期的年份
     * @param month    int类型，表示指定日期的月份  (从0算起)
     * @param date     int类型，表示指定日期的日
     * @param interval 区间，可以输入正数、负数
     *                 正数：往后算n天
     *                 负数：往前算n天
     * @return
     */
    public static boolean isInTimeInterval(int year, int month, int date, int interval) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, interval);
        if (interval > 0) { //5
            return year < c.get(Calendar.YEAR)
                    || (year == c.get(Calendar.YEAR) && month < c.get(Calendar.MONTH))
                    || (year == c.get(Calendar.YEAR) && month <= c.get(Calendar.MONTH) && date < c.get(Calendar.DATE));
        }
        if (interval < 0) { //-5
            return year > c.get(Calendar.YEAR)
                    || (year == c.get(Calendar.YEAR) && month > c.get(Calendar.MONTH))
                    || (year == c.get(Calendar.YEAR) && month >= c.get(Calendar.MONTH) && date > c.get(Calendar.DATE));
        }
        return true;
    }

    /**
     * 判断是不是过去的日期
     *
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static boolean isPastDate(int year, int month, int date) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 0);
        return year < c.get(Calendar.YEAR)
                || (year == c.get(Calendar.YEAR) && (month) < c.get(Calendar.MONTH))
                || (year == c.get(Calendar.YEAR) && (month) <= c.get(Calendar.MONTH) && date < c.get(Calendar.DATE));
    }

    /**
     * 把时间戳的字符串转换成yyyy-MM-dd HH:mm格式
     *
     * @param time String类型的时间戮
     * @return yyyy-MM-dd HH:mm格式的时间
     */
    public static String formatDate(long time) {

        return DATE_FORMAT_HM.format(new Date(time));
    }

    public static String formatDateOnlyHm(long time) {
        if (time != 0)
            return DATE_FORMAT_HM_NO_DATE.format(new Date(time));
        else
            return "";
    }

    /**
     * 时间格式成为 XX小时XX分 样式
     *
     * @param time 时间
     * @return XX小时XX分
     */
    public static String formatDateOnlyHmChs(long time) {
        if (time != 0) {

            String format = DATE_FORMAT_HM_NO_DATE.format(new Date(time));
//            2:30
            String[] strArrays = format.split(":");
            String strArrH = strArrays[0];
            String strArrM = strArrays[1];
            if (TextUtils.equals("00", strArrH))
                return strArrM + "分";
            else if (TextUtils.equals("00", strArrM))
                return strArrH + "小时";
            else
                return strArrH + "小时" + strArrM + "分";
        } else
            return "";
    }

    public static String formatDateOnlyYMD(long time) {

        if (time != 0) {
            return DATE_FORMAT_HM_NO_HMS.format(new Date(time));
        } else {
            return "";
        }

    }


    /**
     * 把yyyy-MM-dd HH:mm的字符串转换成时间戳格式
     *
     * @param time String类型的时间戮
     * @return yyyy-MM-dd HH:mm格式的时间
     */
    public static String getTimestamp(String time) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            Date date = simpleDateFormat.parse(time);
            long timeStemp = date.getTime();
            return timeStemp + "";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 根据传入的格式转换为时间戳
     *
     * @param timeStr
     * @param sdf
     * @return
     */
    public static long getLongTimestamp(String timeStr, SimpleDateFormat sdf) {
        try {
            Date date = sdf.parse(timeStr);
            long timestamp = date.getTime();
            return timestamp;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //将分钟转换成多少小时多少分钟 如 61分钟->1小时1分钟
    public static String getTimeOnlyHm(int min) {

        return min / 60 + "," + min % 60;
    }

    /***********************
     * 消息中心使用
     ****************************/
    public static String formatDateOnlyYMD(String time) {

        if (time != null) {
            long l = Long.parseLong(time);
            if (l != 0) {
                return DATE_FORMAT_HM_NO_HMS.format(new Date(l));
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public static String formatDateOnlyHM(String time) {
        if (time != null) {
            long l = Long.parseLong(time);
            if (l != 0)
                return DATE_FORMAT_HM_NO_DATE.format(new Date(l));
            else
                return "";
        } else {
            return "";
        }
    }
    /***********************消息中心使用结束****************************/

}
