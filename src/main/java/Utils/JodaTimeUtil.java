
package Utils;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Nullable;

import org.joda.time.*;
import org.junit.jupiter.api.Test;

public class JodaTimeUtil {
    /**注：
     * LocalDate：该类型是不包含时间的日期，只包含天数
     * LocalTime：与LocalDate想对照，它是不包含日期的时间。
     * LocalDateTime：包含了日期及时间，没有偏移信息（时区）。
    * */

    /**  这里的时间类型对应的是DateTime    */
    /** 默认日期时间格式 */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_TIME_FORMAT1 = "yyyy/MM/dd HH:mm:ss";
    public static final String DEFAULT_DATE_TIME_FORMAT2 = "yyyy\\MM\\dd HH:mm:ss";
    /** 默认日期格式 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_FORMAT1 = "yyyy/MM/dd";
    /** 默认时间格式 */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";



    /**
     *
     * * 初始化方法
     * * 1、参的构造方法会创建一个在当前系统所在时区的当前时间，精确到毫秒 2017-03-15T12:31:33.517+08:00
     ** 2、DateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour)根据传入的时间构造
     ** 3、DateTime(long instant) 这个构造方法创建出来的实例，是通过一个long类型的时间戳，它表示这个时间戳距1970-01-01T00:00:00Z的毫秒数。使用默认的时区
     * * 4、DateTime(Object instant) 这个构造方法可以通过一个Object对象构造一个实例。这个Object对象可以是这些类型
     *  *DateTime dt = new DateTime();
     *  *DateTime dt1 = new DateTime(2017, 12, 12, 12, 12, 8);
     *  *DateTime dt2 = new DateTime(1487473917004L);
     *  *DateTime dt3 = new DateTime(new Date());
     *  *DateTime dt4 = new DateTime("2017-03-15T12:22:22");
     *  */

/**
     * 获取当前系统时间
 *     @param pattern
 *     1、默认年月日中间用-隔开，时分秒用：且小时为24小时制
 *     2、获取完成的时间传入的参数模板为yyyy（年） MM（月）  dd（日）  hh（12小时制的时） HH（24小时制的时） mm（分）  ss（秒）
 *     3、如果只想获取年月日或者时分秒或者这其中的任一一个组合，按照上面的对应关系组合即可
     * @return yyyy-MM-dd HH:mm:ss
     */
@Nullable
public static String getCurrentTimePattern(String pattern) {
    DateTime dt = new DateTime();
    if (pattern == null||pattern.isEmpty()) return dt.toString(DEFAULT_DATE_TIME_FORMAT);
    return dt.toString(pattern);
    }

   /**
    * 根据指定时间获取对应的时间戳，如果传入的为空，则获取当前时间
    */
   public static long getCurrentTimeForMillis(String pattern) {
         if (pattern == null||pattern.isEmpty()) return new DateTime().getMillis();
       return new DateTime(pattern).getMillis();
   }

    /**
     *    根据传入的时间戳，转化成Datetime类型的时间
     */
    public static DateTime getDateTimeForMillis(long millis) {

        return new DateTime().withMillis(millis);
    }

    /**
     *    根据传入的时间戳，转化成String类型的时间,默认按照yyyy-MM-dd HH:mm:ss
     */
    public static String getStringTimeForMillis(long millis) {
        return new DateTime().withMillis(millis).toString(DEFAULT_DATE_TIME_FORMAT);
    }

/**
     * 按照时区转换时间
     * @param date
     * @param timeZone 时区
     * @param parrten
     * @return
     */
/*

    @Nullable
    public static String format(Date date, TimeZone timeZone, String parrten) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(parrten);
        sdf.setTimeZone(timeZone);
        return sdf.format(date);
    }

    */

/**
     * 获取指定时间
     * @param year 年
     * @param month 月
     * @param day 天
     * @param hour 小时
     * @param minute 分钟
     * @param seconds 秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getPointTime(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer seconds) {
        DateTime dt = new DateTime(year, month, day, hour, minute, seconds);
        String date = dt.toString(DEFAULT_DATE_TIME_FORMAT);
        return date;
    }


/**
     *根据传入的年月日时分秒与自定义的格式，生产string类型的时间
     * @param year 年
     * @param month 月
     * @param day 天
     * @param hour 小时
     * @param minute 分钟
     * @param seconds 秒
     * @param parrten 自定义格式
     * @return parrten
     */
    public static String getPointTimePattern(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer seconds, String parrten) {
        DateTime dt = new DateTime(year, month, day, hour, minute, seconds);
        String date = dt.toString(parrten);
        return date;
    }

/**
   根据传入的String类型的参数，转化成DateTIme类型的时间.支持格式如下：
 1、格式1为yyyy-MM-dd HH:mm:ss
 2、格式2为yyyy-MM-dd  (如果没有传入时分秒，默认会取0时0分0秒)
*/
   public static DateTime  getDatetime(String times){
        if (times==null||times.isEmpty()) return  new DateTime();
        if(times.indexOf(":") !=-1) {
            return new DateTime(times.replaceAll( " ","T")) ;
            }
       String[]  temptime=times.split("-");
        int year=Integer.parseInt(temptime[0]);
        int mounth=Integer.parseInt(temptime[1]);
        int day=Integer.parseInt(temptime[2]);
        return new DateTime(year,mounth,day,0,0,0);

    }

    /**将Datetime类型转成string类型的
     */
    public static  String  GetStringTime(DateTime times){
        return  times.toString(DEFAULT_DATE_TIME_FORMAT);
    }


/**
     * 获取指定日期
     * @param year
     * @param month
     * @param day
     * @return
     */
/*    public static String getPointDate(Integer year, Integer month, Integer day) {
        LocalDate dt = new LocalDate(year, month, day);
        String date = dt.toString(FORTER_DATE);
        return date;
    }

   */

/**
     * 获取指定日期 返回指定格式
     * @param year
     * @param month
     * @param day
     * @param parrten
     * @return
     */

/*    public static String getPointDatParrten(Integer year, Integer month, Integer day, String parrten) {
        LocalDate dt = new LocalDate(year, month, day);
        String date = dt.toString(parrten);
        return date;
    }

   */

/**
 * 根据给定的日期（String类型的），获取对应的是一周星期几
 * @return
 */
public static String getCurrentWeek(String times) {
    DateTime dts = getDatetime(times);
    return getWeek(dts);
}


/**
     * 根据给定的日期（Datetime类型的），获取对应的是一周星期几
     * @return
     */
    public static String getWeek(DateTime dts) {

        String week = null;
        switch (dts.getDayOfWeek()) {
            case DateTimeConstants.SUNDAY:
                week = "星期日";
                break;

            case DateTimeConstants.MONDAY:
                week = "星期一";
                break;

            case DateTimeConstants.TUESDAY:
                week = "星期二";
                break;
            case DateTimeConstants.WEDNESDAY:
                week = "星期三";
                break;
            case DateTimeConstants.THURSDAY:
                week = "星期四";
                break;
            case DateTimeConstants.FRIDAY:
                week = "星期五";
                break;
            case DateTimeConstants.SATURDAY:
                week = "星期六";
            default:
                break;
        }
        return week;
    }


/**
     * 获取当前时间是一周的星期几
     *
     * @return
     */
    public static String getCurrentWeek() {
        DateTime dts = new DateTime();
          return getWeek(dts);
    }



/**
     * 格式化日期字符串
     * @param date 日期
     * @param pattern 日期格式
     * @return
     */
/*

    @Nullable
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    */

/**
String 类型转换成LocalDate
 */
public static LocalDate toLocalDate(String times){

    return getDatetime(times).toLocalDate();
    }
/**
     Datetime 类型转换成LocalDate
*/
    public static LocalDate toLocalDate(DateTime times){

        return times.toLocalDate();
    }



    /**将String类型的时间往前或者往后推n天,但返回Datetime类型。
    */
    public  static DateTime plusDaysToD(String times,int days){
        return  getDatetime(times).plusDays(days);

    }

    /**将String类型的时间往前或者往后推n天,但返回String类型。
     */
    public  static String plusDaysToS(String times,int days){
        DateTime dt=  getDatetime(times).plusDays(days);
        if(times.indexOf(":")!=-1){
            return GetStringTime(dt);
        }else
            return dt.toString(DEFAULT_DATE_FORMAT);
    }

    /**比较2个String类型日期相差几天,如果是负数代表的是day2小
    */
    public static int diffdays(String day1,String day2){
        DateTime d1=getDatetime(day1);
        DateTime d2=getDatetime(day2);
        return diffdays(d1,d2);
    }

    /**比较2个String类型日期相差几天,如果是负数代表的是day2小
     */
    public static int diffdays(DateTime day1,DateTime day2){
         return Days.daysBetween(day1, day2).getDays();
           }
    /**获取2个日期之间相差的绝对值
    */
    public static int diffdays_abs(Object day1,Object day2){
        DateTime d1,d2;
        int days=0;
        if (String.class ==day1.getClass() ){
             d1=getDatetime(String.valueOf(day1));
             days=diffdays(d1,new DateTime(day2));
        }
       else if  (String.class ==day2.getClass()){
             d2=getDatetime(String.valueOf(day2));
             days=diffdays(new DateTime(day1),d2);
        }
        else days=diffdays(new DateTime(day1),new DateTime(day2));
        return Math.abs(days);
    }

    /**比较2个String类型日期相差多少分钟,如果是负数代表的是day2小
     * @return
     */



    @Test
    void test() throws InterruptedException {
        //这个instant实例就是用来获取时间戳的
       /* Instant instant = Instant.now();
        long tt1=instant.getMillis();
        Thread.sleep(1000);
        long tt2=Instant.now().getMillis();
        double tt3=(double)(tt2-tt1)/(1000*60);
        System.out.println(tt3);
        LocalDateTime t1= LocalDateTime.now();
        t1.getMillisOfSecond();
        System.out.println(t1);*/

        Instant instant = Instant.now();

   //（X+2）+[(N-1)*7)]
       /* for (int i = 1; i <=9 ; i++) {
            int add=2+(i-1)*7;
            System.out.println(plusDaysToS("2021-01-20",add));
        }*/

        System.out.println("--------------------");
        System.out.println(plusDaysToS("2021-04-10",10));



       /* long te =new DateTime().getMillis();
        LocalDateTime t1= LocalDateTime.now();
       LocalDateTime t2=new LocalDateTime("2014-10-29 12:11:10");
        System.out.println(te);
        Thread.sleep(100);
        long t2= new DateTime().getMillis();
        System.out.println(t2-te);

        String tes=te.toString(DEFAULT_DATE_TIME_FORMAT);
        System.out.println(te.plusDays(-365));
        System.out.println(plusDaysToS("2021-2-12",8));
        System.out.println(plusDaysToS("2021-2-12 11:22:11",8));
        System.out.println(diffdays_abs(te,"2020-4-20"));//充值
        System.out.println(diffdays_abs(te,"2020-5-6"));//提款
        System.out.println(diffdays_abs(te,"2020-5-21"));//ag
        System.out.println(diffdays_abs(te,"2020-6-10"));//qyy
        System.out.println(diffdays_abs(te,"2020-6-2"));//彩票
        System.out.println(plusDaysToS(tes,-15));  //15天前
        System.out.println(diffdays_abs(te,"2020-6-16"));//彩票*/

    }


/**
     *获取当前时间前几天时间,按指定格式返回
     * @param days
     * @return
     */
/*

    public static String forwardDay(Integer days, String format) {
        DateTime dt = new DateTime();
        DateTime y = dt.minusDays(days);
        return y.toString(format);
    }

    */
/**
     *获取当前时间前几天时间
     * @param days
     * @return
     */
/*
    public static Date forwardDay(Integer days) {
        DateTime dt = new DateTime();
        DateTime y = dt.minusDays(days);
        return y.toDate();
    }
    */
/**
     * 获取指定时间之后或者之前的某一天00:00:00 默认返回当天
     * @param days
     * @return
     *//*

    public static Date day00(Integer days, String date, String zimeZone) throws Throwable {
        DateTime dt;
        TimeZone timeZone;
        try {
            if (StringUtils.isBlank(zimeZone)) {
                timeZone = TimeZone.getDefault();
            } else {
                timeZone = TimeZone.getTimeZone(zimeZone);
            }
            if (StringUtils.isBlank(date)) {
                dt = new DateTime().withZone(DateTimeZone.forTimeZone(timeZone)).toLocalDateTime().toDateTime();
            } else {
                dt = new DateTime(date).withZone(DateTimeZone.forTimeZone(timeZone)).toLocalDateTime().toDateTime();
            }
        } catch (Exception e) {
            throw new Throwable(e);
        }

        DateTime y = dt.minusDays(days).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
        return y.toDate();
    }

    */
/**
     *获取指定时间之后或者之前的某一天23:59:59 默认返回当天
     * @param days 偏移量
     * @return
     *//*

    public static Date day59(Integer days, String date, String zimeZone) throws Throwable {
        DateTime dt;
        TimeZone timeZone;
        try {
            if (StringUtils.isBlank(zimeZone)) {
                timeZone = TimeZone.getDefault();
            } else {
                timeZone = TimeZone.getTimeZone(zimeZone);
            }
            if (StringUtils.isBlank(date)) {

                dt = new DateTime().withZone(DateTimeZone.forTimeZone(timeZone)).toLocalDateTime().toDateTime();
            } else {
                dt = new DateTime(date).withZone(DateTimeZone.forTimeZone(timeZone)).toLocalDateTime().toDateTime();
            }
        } catch (Exception e) {
            throw new Throwable(e);
        }
        DateTime y = dt.minusDays(days).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        return y.toDate();
    }

    */
/**
     * 计算两个时间相差多少天
     * @param startDate
     * @param endDate
     * @return
     *//*

    @Nullable
    public static Integer diffDay(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        DateTime dt1 = new DateTime(startDate);
        DateTime dt2 = new DateTime(endDate);
        int day = Days.daysBetween(dt1, dt2).getDays();
        return Math.abs(day);
    }

    */
/**
     * 获取某月之前,之后某一个月最后一天,24:59:59
     * @return
     *//*

    public static Date lastDay(Date date, Integer month) {
        DateTime dt1;
        if (month == null) {
            month = 0;
        }
        if (date == null) {
            dt1 = new DateTime().minusMonths(month);
        } else {
            dt1 = new DateTime(date).minusMonths(month);
        }
        DateTime lastDay = dt1.dayOfMonth().withMaximumValue().
                withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        return lastDay.toDate();
    }

    */
/**
     *获取某月月之前,之后某一个月第一天,00:00:00
     * @return
     *//*

    public static Date firstDay(Date date, Integer month) {
        DateTime dt1;
        if (month == null) {
            month = 0;
        }
        if (date == null) {
            dt1 = new DateTime().minusMonths(month);
        } else {
            dt1 = new DateTime(date).minusMonths(month);
        }
        DateTime lastDay = dt1.dayOfMonth().withMinimumValue().
                withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
        return lastDay.toDate();
    }

    public static Date addDay(Date date, int offset) {
        DateTime dt1;
        if (date == null) {
            dt1 = new DateTime().plusDays(offset);
            return dt1.toDate();
        }
        dt1 = new DateTime(date).plusDays(offset);
        return dt1.toDate();

    }

    */
/**
     * 传入日期时间与当前系统日期时间的比较,
     * 若日期相同，则根据时分秒来返回 ,
     * 否则返回具体日期
     * @return 日期或者 xx小时前||xx分钟前||xx秒前
     *//*

    @Nullable
    public static String getNewUpdateDateString(Date now, Date createDate) {
        if (now == null || createDate == null) {
            return null;
        }
        Long time = (now.getTime() - createDate.getTime());
        if (time > (24 * 60 * 60 * 1000)) {
            return time / (24 * 60 * 60 * 1000) + "天前";
        } else if (time > (60 * 60 * 1000)) {
            return time / (60 * 60 * 1000) + "小时前";
        } else if (time > (60 * 1000)) {
            return time / (60 * 1000) + "分钟前";
        } else if (time >= 1000) {
            return time / 1000 + "秒前";
        }
        return "刚刚";
    }*/
  /**
   一：DateTime与java.util.Date对象,当前系统TimeMillis转换
   二：各种get方法
   * 最大单位是年，最小单位毫秒
   * api中均可以最大单位下的子类

   */
  private  void datetest(){
      DateTime dt = new DateTime();
      DateTime temp1 = new DateTime(new Date());
      Date temp2 = dt.toDate();
      DateTime temp3 = new DateTime(System.currentTimeMillis());

      Calendar calendar = Calendar.getInstance();
      DateTime  temp4 = new DateTime(calendar);


      //获取当前时间的年
      int year = dt.getYear();
      //获取当前时间的月
      int month = dt.getMonthOfYear();
      //获取当前时间是一年中的第几天
      int dayOfYear = dt.getDayOfYear();
      //获取一个月中的天
      int day =  dt.getDayOfMonth();
      //获取一周中的周几
      int week = dt.getDayOfWeek();
      //一天中的第几小时(取整)
      int hour = dt.getHourOfDay();
      //获取星期年
      int weekOfyear =  dt.getWeekyear();
      //当前时间的秒中的毫秒
      int ms = dt.getMillisOfSecond();
      //获取当前时间的秒
      int second = dt.getSecondOfDay();
      //获取当前时间的毫秒
      long millis = dt.getMillis();


  }
}

