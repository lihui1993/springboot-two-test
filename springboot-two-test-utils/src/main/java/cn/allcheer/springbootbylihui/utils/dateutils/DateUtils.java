package cn.allcheer.springbootbylihui.utils.dateutils;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author lihui
 */
@Slf4j
public class DateUtils {
    public static final String DATE_FORMATTER ="yyyy-MM-dd";
    public static final String TIME_FORMATTER ="HH:mm:ss";
    public static final String DATE_TIME_FORMATTER ="yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_MILLIS_FORMATTER ="yyyy-MM-dd HH:mm:ss:SSS";

    public static final String CUSTOM_DATE_FORMATTER ="yyyyMMdd";
    public static final String CUSTOM_TIME_FORMATTER ="HHmmss";
    public static final String CUSTOM_DATE_TIME_FORMATTER ="yyyyMMddHHmmss";
    public static final String CUSTOM_DATE_TIME_MILLIS_FORMATTER ="yyyyMMddHHmmssSSS";

    public static LocalDateTime getCustomDateFormatterLDTime(){
        return stringToLocalDateTimeByPattern(getCustomDateFormatterString(), CUSTOM_DATE_FORMATTER);
    }
    public static String getCustomDateFormatterString(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(CUSTOM_DATE_FORMATTER));
    }

    public static LocalDateTime getCustomTimeFormatterLDTime(){
        return stringToLocalDateTimeByPattern(getCustomTimeFormatterString(), CUSTOM_TIME_FORMATTER);
    }
    public static String getCustomTimeFormatterString(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(CUSTOM_TIME_FORMATTER));
    }

    public static LocalDateTime getCustomDateTimeFormatterLDTime(){
        return stringToLocalDateTimeByPattern(getCustomDateTimeFormatterString(), CUSTOM_DATE_TIME_FORMATTER);
    }
    public static String getCustomDateTimeFormatterString(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(CUSTOM_DATE_TIME_FORMATTER));
    }

    public static LocalDateTime getCustomDateTimeMillisFormatterLDTime(){
        return stringToLocalDateTimeByPattern(getCustomDateTimeMillisFormatterString(), CUSTOM_DATE_TIME_MILLIS_FORMATTER);
    }
    public static String getCustomDateTimeMillisFormatterString(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(CUSTOM_DATE_TIME_MILLIS_FORMATTER));
    }

    public static LocalDateTime getDateFormatterLDTime(){
        return stringToLocalDateTimeByPattern(
                getDateFormatterString(), DATE_FORMATTER);
    }
    public static String getDateFormatterString(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMATTER));
    }

    public static LocalDateTime getTimeFormatterLDTime(){
        return stringToLocalDateTimeByPattern(
                getTimeFormatterString(), TIME_FORMATTER);
    }
    public static String getTimeFormatterString(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIME_FORMATTER));
    }

    public static LocalDateTime getDateTimeFormatterLDTime(){
        return stringToLocalDateTimeByPattern(getDateTimeFormatterString(), DATE_TIME_FORMATTER);
    }
    public static String getDateTimeFormatterString(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER));
    }

    public static LocalDateTime getDateTimeMillisFormatterLDTime(){
        return stringToLocalDateTimeByPattern(getDateTimeMillisFormatterString(), DATE_TIME_MILLIS_FORMATTER);
    }
    public static String getDateTimeMillisFormatterString(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_MILLIS_FORMATTER));
    }
    /**
     * 将LocalDateTime对象转成指定格式的字符串日期
     * @param localDateTime LocalDateTime对象
     * @param pattern 指定的字符串格式，DateUtils中的常量列举了常用的格式，建议使用
     * @return 返回指定格式的字符串日期
     */
    public static String localDateTimeToStringByPattern(LocalDateTime localDateTime,String pattern){
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将源目标的字符串日期转换成指定格式的字符串日期
     * @param sourceTarget 源目标日期字符串
     * @param sourceTargetPattern 源目标日期字符串的格式
     * @param targetPattern （要转换成的）目标日期字符串的格式
     * @return 目标日期字符串
     */
    public static String convertToLDTSByPattern(String sourceTarget, String sourceTargetPattern, String targetPattern){
        return LocalDateTime
                .parse(sourceTarget,DateTimeFormatter.ofPattern(sourceTargetPattern))
                .format(DateTimeFormatter.ofPattern(targetPattern));
    }

    /**
     * 将LocalDateTime对象转换成系统默认时区的Date对象
     * @param localDateTime 源目标LocalDateTime对象
     * @return 目标对象--Date对象
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        return localDateTimeToDateByZoneId(localDateTime,ZoneId.systemDefault());
    }

    /**
     * 将LocalDateTime对象转换成指定时区的Date对象
     * @param localDateTime 源目标LocalDateTime对象
     * @param zoneId 指定的时区
     * @return 目标对象--Date对象
     */
    public static Date localDateTimeToDateByZoneId(LocalDateTime localDateTime,ZoneId zoneId){
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }

    /**
     * 将LocalDateTime对象转换成指定格式的Date对象
     * @param localDateTime 源目标LocalDateTime对象
     * @param pattern 指定的格式
     * @return 目标对象--Date对象
     */
    public static Date localDateTimeToDateByPattern(LocalDateTime localDateTime,String pattern){
        return localDateTimeToDate(
                stringToLocalDateTimeByPattern(
                        localDateTimeToStringByPattern(localDateTime,pattern) ,pattern
                )
        );
    }

    /**
     * 将给定的源目标字符串日期转成LocalDateTime对象
     * 日期部分必须包括Time部分，不然会出错，出错原因目前知道是在LocalDateTime的静态方法parse()报的错误信息
     * LocalDateTime#parse()在做转换时，会把sourceTarget拆解成date和time，如果sourceTarget没有包含time部分，将会抛出异常
     * @param sourceTarget 源目标字符串日期
     * @param sourceTargetPattern 源目标日期格式，DateUtils中的常量列举了常用的格式，建议使用
     * @return 目标日期--LocalDateTime对象
     */
    public static LocalDateTime stringToLocalDateTimeByPattern(String sourceTarget,String sourceTargetPattern){
        return LocalDateTime.parse(sourceTarget,DateTimeFormatter.ofPattern(sourceTargetPattern));
    }

    /**
     * 将Date对象转成系统默认时区的LocalDateTime对象
     * @param date 源目标对象--Date
     * @return 目标对象--LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date){
        return dateToLocalDateTimeByZoneId(date,ZoneId.systemDefault());
    }

    /**
     * 将Date对象转成指定时区的LocalDateTime对象
     * @param date 源目标对象--Date
     * @param zoneId 指定时区对象--ZoneId
     * @return 目标对象--LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTimeByZoneId(Date date,ZoneId zoneId){
        return LocalDateTime.ofInstant(date.toInstant(),zoneId);
    }

    /**
     * 将Date对象转换成指定格式的LocalDateTime对象
     * @param date 源目标对象--Date
     * @param pattern 指定格式
     * @return 目标对象--LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTimeByPattern(Date date,String pattern){
        return stringToLocalDateTimeByPattern(
                localDateTimeToStringByPattern(
                        dateToLocalDateTime(date),pattern ),pattern );
    }

    /**
     * 计算两个时间(字符串格式的日期)之间相差的秒数
     * @param startLocalDateTime 开始时间
     * @param startLDTPattern 开始时间的日期格式
     * @param endLocalDateTime 结束时间
     * @param endLDTPattern 结束时间的日期格式
     * @return 相差的秒数--Long对象
     */
    public static Long durationSecondsBetween(String startLocalDateTime,String startLDTPattern,String endLocalDateTime,String endLDTPattern){
        return Duration.between(
                stringToLocalDateTimeByPattern(startLocalDateTime,startLDTPattern),
                stringToLocalDateTimeByPattern(endLocalDateTime,endLDTPattern)
        ).getSeconds();
    }

    /**
     * 计算两个时间(LocalDateTime对象)之间相差的秒数
     * @param startLocalDateTime 开始时间
     * @param endLocalDateTime 结束时间
     * @return 相差的秒数--Long对象
     */
    public static Long durationSecondsBetween(LocalDateTime startLocalDateTime,LocalDateTime endLocalDateTime){
        return Duration.between(startLocalDateTime,endLocalDateTime).getSeconds();
    }

    /**
     * 计算两个时间(字符串格式的日期)之间相差的毫秒数
     * @param startLocalDateTime 开始时间
     * @param startLDTPattern 开始时间的日期格式
     * @param endLocalDateTime 结束时间
     * @param endLDTPattern 结束时间的日期格式
     * @return 相差的毫秒数--Long对象
     */
    public static Long durationMillisBetween(String startLocalDateTime,String startLDTPattern,String endLocalDateTime,String endLDTPattern){
        return Duration.between(
                stringToLocalDateTimeByPattern(startLocalDateTime,startLDTPattern),
                stringToLocalDateTimeByPattern(endLocalDateTime,endLDTPattern)
        ).toMillis();
    }

    /**
     * 计算两个时间(LocalDateTime对象)之间相差的
     * @param startLocalDateTime 开始时间
     * @param endLocalDateTime 结束时间
     * @return 相差的毫秒数--Long对象
     */
    public static Long durationMillisBetween(LocalDateTime startLocalDateTime,LocalDateTime endLocalDateTime){
        return Duration.between(startLocalDateTime,endLocalDateTime).toMillis();
    }

    /**
     * 计算两个时间(字符串格式的日期)之间相差的天数
     * 内部用Duration对象计算，用的是LocalDateTime对象
     * @param startLocalDateTime 开始时间
     * @param startLDTPattern 开始时间的日期格式
     * @param endLocalDateTime 结束时间
     * @param endLDTPattern 结束时间的日期格式
     * @return 相差的天数--Long
     */
    public static Long durationDaysBetween(String startLocalDateTime,String startLDTPattern,String endLocalDateTime,String endLDTPattern){
        return Duration.between(
                stringToLocalDateTimeByPattern(startLocalDateTime,startLDTPattern),
                stringToLocalDateTimeByPattern(endLocalDateTime,endLDTPattern)
        ).toDays();
    }

    /**
     * 计算两个时间(LocalDateTime)之间相差的天数
     * 内部用Duration对象计算，用的是LocalDateTime对象
     * @param startLocalDateTime 开始时间
     * @param endLocalDateTime 结束时间
     * @return 相差的天数--Long
     */
    public static Long durationDaysBetween(LocalDateTime startLocalDateTime,LocalDateTime endLocalDateTime){
        return Duration.between(startLocalDateTime,endLocalDateTime).toDays();
    }

    /**
     * 计算两个时间(字符串格式的日期)之间相差的天数
     * 内部用Period对象计算，用的是LocalDate对象
     * @param startLocalDateTime 开始时间
     * @param startLDTPattern 开始时间的日期格式
     * @param endLocalDateTime 结束时间
     * @param endLDTPattern 结束时间的日期格式
     * @return 相差的天数--Long
     */
    public static Integer periodDaysBetween(String startLocalDateTime,String startLDTPattern,String endLocalDateTime,String endLDTPattern){
        return Period.between(
                stringToLocalDateTimeByPattern(startLocalDateTime,startLDTPattern).toLocalDate(),
                stringToLocalDateTimeByPattern(endLocalDateTime,endLDTPattern).toLocalDate()
        ).getDays();
    }

    /**
     * 计算两个时间(LocalDateTime)之间相差的天数
     * 内部用Period对象计算，用的是LocalDate对象
     * @param startLocalDateTime 开始时间
     * @param endLocalDateTime 结束时间
     * @return 相差的天数--Long
     */
    public static Integer periodDaysBetween(LocalDateTime startLocalDateTime,LocalDateTime endLocalDateTime){
        return Period.between(startLocalDateTime.toLocalDate(),endLocalDateTime.toLocalDate()).getDays();
    }

    /**
     * 计算两个日期(字符串格式)之间相差的年数
     * @param startLocalDateTime 开始时间
     * @param startLDTPattern 开始时间的日期格式
     * @param endLocalDateTime 结束时间
     * @param endLDTPattern 结束时间的日期格式
     * @return 相差的年数--Integer对象
     */
    public static Integer periodYearsBetween(String startLocalDateTime,String startLDTPattern,String endLocalDateTime,String endLDTPattern){
        return Period.between(
                stringToLocalDateTimeByPattern(startLocalDateTime,startLDTPattern).toLocalDate(),
                stringToLocalDateTimeByPattern(endLocalDateTime,endLDTPattern).toLocalDate()
        ).getYears();
    }

    /**
     * 计算两个日期(LocalDateTime)之间相差的年数
     * @param startLocalDateTime 开始时间
     * @param endLocalDateTime 结束时间
     * @return 相差的年数--Integer对象
     */
    public static Integer periodYearsBetween(LocalDateTime startLocalDateTime,LocalDateTime endLocalDateTime){
        return Period.between(
                startLocalDateTime.toLocalDate(),
                endLocalDateTime.toLocalDate()
        ).getYears();
    }

    /**
     * 计算两个日期(LocalDate)之间相差的年数
     * @param startLocalDate 开始时间
     * @param endLocalDate 结束时间
     * @return 相差的年数--Integer对象
     */
    public static Integer periodYearsBetween(LocalDate startLocalDate,LocalDate endLocalDate){
        return Period.between(startLocalDate,endLocalDate).getYears();
    }

    /**
     * 获取指定日期所在月份的第一天的日期
     * @param localDateTime 源目标--指定日期
     * @return 目标日期--LocalDateTime对象
     */
    public static LocalDateTime getFirstDateTimeOfMonth(LocalDateTime localDateTime){
        return localDateTime.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取指定日期所在月份的最后一天的日期
     * @param localDateTime 源目标--指定日期
     * @return 目标日期--LocalDateTime对象
     */
    public static LocalDateTime getLastDateTimeOfMonth(LocalDateTime localDateTime){
        return localDateTime.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取指定日期所在周的第一天(周一)
     * @param localDateTime 源目标--指定日期
     * @return 目标日期--LocalDateTime对象
     */
    public static LocalDateTime getFirstDateTimeOfWeek(LocalDateTime localDateTime){
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        int days = DayOfWeek.MONDAY.getValue() - dayOfWeek.getValue();
        if(days==0){
            return localDateTime;
        }else{
            return localDateTime.plusDays(days);
        }
    }

    /**
     * 获取指定日期所在周的最后一天(周日)
     * @param localDateTime 源目标--指定日期
     * @return 目标日期--LocalDateTime对象
     */
    public static LocalDateTime getLastDateTimeOfWeek(LocalDateTime localDateTime){
        DayOfWeek dayOfWeek=localDateTime.getDayOfWeek();
        int days=DayOfWeek.SUNDAY.getValue()-dayOfWeek.getValue();
        if(days==0){
            return localDateTime;
        }else{
            return localDateTime.plusDays(days);
        }
    }
    /**
     * 获取指定日期所在月份的第一天的日期
     * @param localDate 源目标--指定日期
     * @return 目标日期--LocalDateTime对象
     */
    public static LocalDate getFirstDateOfMonth(LocalDate localDate){
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取指定日期所在月份的最后一天的日期
     * @param localDate 源目标--指定日期
     * @return 目标日期--LocalDateTime对象
     */
    public static LocalDate getLastDateOfMonth(LocalDate localDate){
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取指定日期所在周的第一天(周一)
     * @param localDate 源目标--指定日期
     * @return 目标日期--LocalDateTime对象
     */
    public static LocalDate getFirstDateOfWeek(LocalDate localDate){
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int days = DayOfWeek.MONDAY.getValue() - dayOfWeek.getValue();
        if(days==0){
            return localDate;
        }else{
            return localDate.plusDays(days);
        }
    }

    /**
     * 获取指定日期所在周的最后一天(周日)
     * @param localDate 源目标--指定日期
     * @return 目标日期--LocalDateTime对象
     */
    public static LocalDate getLastDateOfWeek(LocalDate localDate){
        DayOfWeek dayOfWeek=localDate.getDayOfWeek();
        int days=DayOfWeek.SUNDAY.getValue()-dayOfWeek.getValue();
        if(days==0){
            return localDate;
        }else{
            return localDate.plusDays(days);
        }
    }

}
