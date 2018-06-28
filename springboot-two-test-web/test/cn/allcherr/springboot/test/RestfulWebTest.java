package cn.allcherr.springboot.test;

import cn.allcheer.springbootbylihui.springboottwotestweb.SpringbootTwoTestWebApplication;
import cn.allcheer.springbootbylihui.utils.dateutils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.TimeZone;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootTwoTestWebApplication.class)
@Slf4j
public class RestfulWebTest {
    private TestRestTemplate restTemplate=new TestRestTemplate();
    @Test
    public void testVerifyCodeLogin(){
        String url = "http://192.168.2.69:"+6100+"/api/verifyCode/login";
        String reqJsonStr = "{\"header\":{\"service\":\"03\",\"inputCharset\":\"lihui\",\"requestDate\":\"20180515\",\"requestTime\":\"181809\",\"requestId\":\"89\"}},"+
                "{\"body\":{\"mobNo\":\"13687352585\",\"verifyCode\":\"344556\",\"smsVcDate\":\"20180515\",\"smsVcId\":\"3444\",\"channel\":\"00\"}}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(reqJsonStr,headers);
        /*ResponseEntity<ClickRecordResponseBoby> responseEntity = null;
        try{
            responseEntity= template.postForEntity(url, entity, ClickRecordResponseBoby.class);
            System.out.println(responseEntity.getBody().toString());
        }catch (Exception e){
            log.error("···{}",e.getLocalizedMessage());
        }*/
        String re=restTemplate.postForObject(url, entity,String.class);
        log.info("调用返回信息是：{}",re);
    }

    @Test
    public void testDateUtils(){
        log.info("\n-----------------------------------------" +
                 "\n \t\t[String::::::::===========DATE_FORMATTER:{}]"
                , DateUtils.getLDTByPattern(DateUtils.DATE_FORMATTER));
        log.info("\n \t\t[String::::::::===========TIME_FORMATTER:{}]"
                , DateUtils.getLDTByPattern(DateUtils.TIME_FORMATTER));
        log.info("\n \t\t[String::::::::===========DATE_TIME_FORMATTER:{}]"
                , DateUtils.getLDTByPattern(DateUtils.DATE_TIME_FORMATTER));
        log.info("\n \t\t[String::::::::===========DATE_TIME_MILLIS_FORMATTER:{}]"
                , DateUtils.getLDTByPattern(DateUtils.DATE_TIME_MILLIS_FORMATTER));

        log.info("\n \t\t[String::::::::===========CUSTOM_DATE_FORMATTER:{}]"
                , DateUtils.getLDTByPattern(DateUtils.CUSTOM_DATE_FORMATTER));
        log.info("\n \t\t[String::::::::===========CUSTOM_TIME_FORMATTER:{}]"
                , DateUtils.getLDTByPattern(DateUtils.CUSTOM_TIME_FORMATTER));
        log.info("\n \t\t[String::::::::===========CUSTOM_DATE_TIME_FORMATTER:{}]"
                , DateUtils.getLDTByPattern(DateUtils.CUSTOM_DATE_TIME_FORMATTER));
        log.info("\n \t\t[String::::::::===========CUSTOM_DATE_TIME_MILLIS_FORMATTER:{}]"
                , DateUtils.getLDTByPattern(DateUtils.CUSTOM_DATE_TIME_MILLIS_FORMATTER));

        log.info("\n \t\t[LocalDate:::::=======DATE_FORMATTER:{}]"
                ,DateUtils.getDateFormatterLDate().toString());
        log.info("\n \t\t[LocalTime:::::=======TIME_FORMATTER:{}]"
                ,DateUtils.getTimeFormatterLTime().toString());
        log.info("\n \t\t[LocalDateTime:::::=======DATE_TIME_FORMATTER:{}]"
                ,DateUtils.getDateTimeFormatterLDTime().toString());
        log.info("\n \t\t[LocalDateTime:::::=======DATE_TIME_MILLIS_FORMATTER:{}]"
                ,DateUtils.getDateTimeMillisFormatterLDTime().toString());

        log.info("\n \t\t[LocalDate:::::=======CUSTOM_DATE_FORMATTER:{}]"
                ,DateUtils.getCustomDateFormatterLDate().toString());
        log.info("\n \t\t[LocalTime:::::=======CUSTOM_TIME_FORMATTER:{}]"
                ,DateUtils.getCustomTimeFormatterLTime().toString());
        log.info("\n \t\t[LocalDateTime:::::=======CUSTOM_DATE_TIME_FORMATTER:{}]"
                ,DateUtils.getCustomDateTimeFormatterLDTime().toString());

        LocalDateTime nowLocalDateTime = LocalDateTime.now();
        log.info("\n \t\t[String::::::::===========localDateTimeToStringByPattern:{}]"
                ,DateUtils.localDateTimeToStringByPattern(nowLocalDateTime,DateUtils.CUSTOM_DATE_FORMATTER));
        log.info("\n \t\t[String::::::::===========localDateTimeToStringByPattern:{}]"
                ,DateUtils.localDateTimeToStringByPattern(nowLocalDateTime,DateUtils.CUSTOM_TIME_FORMATTER));
        log.info("\n \t\t[String::::::::===========localDateTimeToStringByPattern:{}]"
                ,DateUtils.localDateTimeToStringByPattern(nowLocalDateTime,DateUtils.CUSTOM_DATE_TIME_FORMATTER));
        log.info("\n \t\t[String::::::::===========localDateTimeToStringByPattern:{}]"
                ,DateUtils.localDateTimeToStringByPattern(nowLocalDateTime,DateUtils.CUSTOM_DATE_TIME_MILLIS_FORMATTER));

        log.info("\n \t\t[String::::::::===========convertLDTToStringSByPattern:{}]"
                ,DateUtils.convertLDTToStringSByPattern("19930209235630",DateUtils.CUSTOM_DATE_TIME_FORMATTER,DateUtils.DATE_FORMATTER));
        log.info("\n \t\t[String::::::::===========convertLDTToStringSByPattern:{}]"
                ,DateUtils.convertLDTToStringSByPattern("19930209235630",DateUtils.CUSTOM_DATE_TIME_FORMATTER,DateUtils.TIME_FORMATTER));
        log.info("\n \t\t[String::::::::===========convertLDTToStringSByPattern:{}]"
                ,DateUtils.convertLDTToStringSByPattern("19930209235630",DateUtils.CUSTOM_DATE_TIME_FORMATTER,DateUtils.DATE_TIME_FORMATTER));
        log.info("\n \t\t[String::::::::===========convertLDTToStringSByPattern:{}]"
                ,DateUtils.convertLDTToStringSByPattern("19930209235630",DateUtils.CUSTOM_DATE_TIME_FORMATTER,DateUtils.DATE_TIME_MILLIS_FORMATTER));

        log.info("\n \t\t[String::::::::===========convertLDTToStringSByPattern:{}]"
                ,DateUtils.convertLDTToStringSByPattern("1993-02-09",DateUtils.DATE_FORMATTER,DateUtils.CUSTOM_DATE_FORMATTER));
        log.info("\n \t\t[String::::::::===========convertLDTToStringSByPattern:{}]"
                ,DateUtils.convertLDTToStringSByPattern("1993-02-09",DateUtils.DATE_FORMATTER,DateUtils.CUSTOM_TIME_FORMATTER));
        log.info("\n \t\t[String::::::::===========convertLDTToStringSByPattern:{}]"
                ,DateUtils.convertLDTToStringSByPattern("1993-02-09",DateUtils.DATE_FORMATTER,DateUtils.CUSTOM_DATE_TIME_FORMATTER));
        log.info("\n \t\t[String::::::::===========convertLDTToStringSByPattern:{}]"
                ,DateUtils.convertLDTToStringSByPattern("1993-02-09",DateUtils.DATE_FORMATTER,DateUtils.CUSTOM_DATE_TIME_MILLIS_FORMATTER));

        log.info("\n \t\t[Date::::::::===========localDateTimeToDate:{}]"
                ,DateUtils.localDateTimeToDate(nowLocalDateTime).toString());

        String zoneIds[] = TimeZone.getAvailableIDs();
        for(int i=0;i<zoneIds.length/2;i++) {
            log.info("\n \t\t[Date::::::::===========localDateTimeToDateByZoneId:{}=====ZoneId:{}]"
                    , DateUtils.localDateTimeToDateByZoneId(nowLocalDateTime, ZoneId.of(TimeZone.getAvailableIDs()[0])).toString()
                    , TimeZone.getAvailableIDs()[i]);
        }

        log.info("\n [===========stringToLocalDateTimeByPattern:{}]"
                ,DateUtils.stringToLocalDateTimeByPattern("19930209235630",DateUtils.CUSTOM_DATE_TIME_FORMATTER).toString());

        log.info("\n [===========getFirstDateTimeOfMonth:{}]"
                ,DateUtils.localDateTimeToStringByPattern( DateUtils.getFirstDateTimeOfMonth(DateUtils.getDateTimeFormatterLDTime()),DateUtils.DATE_TIME_FORMATTER) );

        log.info("\n [===========getFirstDateTimeOfWeek:{}]"
                ,DateUtils.localDateTimeToStringByPattern( DateUtils.getFirstDateTimeOfWeek(DateUtils.getDateTimeFormatterLDTime()),DateUtils.DATE_TIME_FORMATTER));


        log.info("\n [===========getLastDateTimeOfWeek:{}]\n-----------------------------------------"
                ,DateUtils.localDateTimeToStringByPattern( DateUtils.getLastDateTimeOfWeek(DateUtils.getDateTimeFormatterLDTime()),DateUtils.DATE_TIME_FORMATTER) );
    }

}
