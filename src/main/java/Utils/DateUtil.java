package Utils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;


import javax.annotation.Nullable;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

/**
*  处理时间的工具类,使用jdk1.8自带的localdate
 *  一：其中LocalDate中的DAYS_0000_TO_1970常量的含义与计算逻辑：
 *  从公元0年到1970年1月1日，间隔的天数
 *  1、从0000年到1970年过了5个400年，多了30年，这30年中有7个闰年(多一个闰年就多一天)
 *  2、所以(DAYS_PER_CYCLE * 5L) - (30L * 365L + 7L)
 *
 * 二：参考下DateTimeFormatter与DateTimeFormatterBuilder
 *
 * 三：根据传入的模板格式（比如yyyy-mm-dd）返回对应的时间值（String类型的）,如果模板格式为空，按照默认的模板格式返回（yyyy-MM-dd HH:mm:ss）
 *
 * 四：根据传入的模板格式，返回对应的时间戳。
 *
*  */
public class DateUtil {
    /** 默认日期时间格式 */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_TIME_FORMAT1 = "yyyy/MM/dd HH:mm:ss";
    public static final String DEFAULT_DATE_TIME_FORMAT2 = "yyyy\\MM\\dd HH:mm:ss";
    /** 默认日期格式 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_FORMAT1 = "yyyy/MM/dd";
    /** 默认时间格式 */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

//    public static Date addDays (Date date,int amount){
//        Calendar cd = Calendar.getinstance();
//        cd.setTime(date);
//        cd.add(Calendar.DATE,amount);
//        return cd.getTime();
//    }
/**
1、根据传入的时间格式的样式，比如yyyy-MM-dd HH:mm:ss转成时间戳
 2、使用的时区为东八区
    */
  public long TomilliSecond(String time){
      long times = 0;
      LocalDateTime  temptime;
        if (null == time || time.isEmpty()||"0".equals(time)) {
          times=LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();}
        else {
            temptime=LocalDateTime.parse(time, DateTimeFormatter.ofPattern(getformattime(time)));
            times=temptime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        }
      return times;
  }

 public  String GetNowTime(String pattern){
      if(null == pattern || pattern.isEmpty()) return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT));
     for (int pos = 0; pos < pattern.length(); pos++){
         char cur = pattern.charAt(pos);
        /* if(cur=='y' || cur='Y')*/
     }
         String finalpattern=getformattime(pattern);
      return LocalDateTime.now().format(DateTimeFormatter.ofPattern(finalpattern));
 }

  /**
   * 根据传入的时间内容取对应的模板；
   * 第一种是使用-隔开
   * 第二种是使用/隔开
   * 第三种是使用\隔开
  */
  private String getformattime(String datetime){
      String timeformatter;
      if (datetime.indexOf("-")!=-1) {
          timeformatter=DEFAULT_DATE_TIME_FORMAT;

      }else if (datetime.indexOf("/")!=-1) {
          timeformatter=DEFAULT_DATE_TIME_FORMAT1;
      }else {
          timeformatter=DEFAULT_DATE_TIME_FORMAT2;
      }
      return timeformatter;
  }

@Test
void  testmill(){
    System.out.println(TomilliSecond(""));
    //System.out.println(milliSecond("2020-05-13 21:12:11"));
    System.out.println(TomilliSecond("2020/05/13 21:12:11"));
    System.out.println(TomilliSecond("2020\\05\\13 21:12:11"));
    System.out.println(" dbab" .contains("a"));
    System.out.println("r\\edar".indexOf("\\"));
    System.out.println("qw/dar".indexOf("/"));

    System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
}
    @Nullable
    public static String getCurrentTimePattern(String pattern) {
        DateTime dt = new DateTime();
      if (pattern == null || pattern.isEmpty()) return dt.toString(DEFAULT_DATE_TIME_FORMAT);
        return dt.toString(pattern);

    }

    public static long getTimeForMillis(String pattern) {
        if (pattern == null||pattern.isEmpty()) return new DateTime().getMillis();
        return new DateTime(pattern).getMillis();
    }
    @Test
    void  testtime() throws ParseException {
          /* System.out.println(getCurrentTimePattern(""));
        System.out.println(new DateTime().getMillis());
        System.out.println(getTimeForMillis(""));
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-12-21 01:21:22");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-12-21 01:21:22"));
        //DateTime dt4 = new DateTime("2017\\03\\15T12:22:22");
       // System.out.println(dt4);
            System.out.println(new DateTime().withMillis(1591177004517L).toString("yyyy\\MM\\dd HH:mm:ss"));
        String times= "2021-12-21 01:21:22".replaceAll(" ","T");
        System.out.println(new DateTime(times));
        System.out.println("2021-12-21 01:21:22".indexOf(":"));*/
//        String[]  vale="2021-12-21 01:21:22".split(" ");
//        for(String a:vale){
//            System.out.println(a);
//        }

       // System.out.println(new DateTime(2112,12,21,0,0,0));
        System.out.println(new DateTime().toLocalDate());

    }

    @Test
    public void daydifftest() throws NoSuchFieldException {
       /* LocalDate date = LocalDate.now();
        System.out.println(date.toString());
        System.out.println(date.plusDays(-146097));
        System.out.println(date.plusYears(-400));
        System.out.println(date.plusDays(-62));
        System.out.println(date.plusDays(-2));
        //获取毫秒数
        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(milliSecond);
        long days=100L;
        .toString("yyyy-MM-dd HH:mm:ss")
        */
         DateTime dt=new DateTime("2020-10-12T00:00:00");
        // dt=dt.plusDays(9);
        System.out.println(dt.plusDays(-15));
        System.out.println(dt.plusDays(-100));
        System.out.println(dt.getDayOfMonth());
        System.out.println(Days.daysBetween(dt, new DateTime("2020-06-3T00:00:00")).getDays());
        Object te1=new DateTime();
        DateTime t1=new DateTime(te1);
        System.out.println(te1+"  "+t1);

        // DAYS_0000_TO_1970   从公元0年到1970年1月1日，间隔的天数
        //从0000年到1970年过了5个400年，多了30年，这30年中有7个闰年(多一个闰年就多一天)
        // (DAYS_PER_CYCLE * 5L) - (30L * 365L + 7L)
        /*Field fields =date.getClass().getDeclaredField("DAYS_0000_TO_1970");
        fields.setAccessible(true);
        try {
            System.out.println(fields.get(date));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
    }


}
