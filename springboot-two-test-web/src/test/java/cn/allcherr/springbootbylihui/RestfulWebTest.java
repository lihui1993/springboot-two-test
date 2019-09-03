package cn.allcherr.springbootbylihui;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.dao.SysUser;
import cn.allcheer.springbootbylihui.springboottwotestdal.domain.repository.SysUserRepository;
import cn.allcheer.springbootbylihui.springboottwotestweb.SpringbootTwoTestWebApplication;
import cn.allcheer.springbootbylihui.utils.dateutils.DateUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootTwoTestWebApplication.class)
@Slf4j
public class RestfulWebTest {

    private TestRestTemplate restTemplate;

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Before
    public void setTemplate(){
        restTemplate= new TestRestTemplate();
        restTemplate.getRestTemplate()
                .getMessageConverters()
                .set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
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

    @Test
    public void initSysUser(){
        SysUser sysUser = new SysUser();
        sysUser.setUserName("lihui");
        sysUser.setPassWord(passwordEncoder.encode("460739"));
        SysUser initSysUser = sysUserRepository.saveAndFlush(sysUser);
        log.info(initSysUser.toString());
    }


}
