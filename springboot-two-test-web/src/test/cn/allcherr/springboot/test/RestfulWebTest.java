package cn.allcherr.springboot.test;

import cn.allcheer.springbootbylihui.springboottwotestweb.SpringbootTwoTestWebApplication;
import cn.allcheer.springbootbylihui.utils.dateutils.DateUtils;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootTwoTestWebApplication.class)
@Slf4j
public class RestfulWebTest {
    private TestRestTemplate restTemplate;

    @Before
    public void setTemplate(){
        restTemplate= new TestRestTemplate();
        restTemplate.getRestTemplate()
                .getMessageConverters()
                .set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }
    private String ipPort="http://"+"192.168.2.76"+":5003";

    @Test
    public void testRe(){
        String url=ipPort+"/api/receive";
        HttpClient client = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("phone", "18852951351"));
        formparams.add(new BasicNameValuePair("pwd", "123456syh"));
        formparams.add(new BasicNameValuePair("crawl_type", "jjd"));

        formparams = sortNameValuePair(formparams);

        formparams.add(new BasicNameValuePair("callback", "http://192.168.2.69:8081/app-server/callback"));

        String sign=getSignString(formparams);

        formparams.add(new BasicNameValuePair("number", "10010"));
        formparams.add(new BasicNameValuePair("autograph", sign));
        UrlEncodedFormEntity uefEntity = null;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8"); //编码
        } catch (UnsupportedEncodingException e) {
            log.error("对请求参数编码出错，信息是:{}",e);
        }
        httppost.setEntity(uefEntity);
        HttpResponse response = null;
        try {
            response = client.execute(httppost);
            org.apache.http.HttpEntity entity =  response.getEntity();
            if (entity != null) {
                log.info("-----------------------------------------------------------");
                log.info("\nResponse content:\n{}", EntityUtils.toString(entity, "UTF-8"));
                log.info("-----------------------------------------------------------");
            }
        } catch (IOException e) {
            log.error("IOException:{}",e);
        }
    }


    @Test
    public void testStatusPoll(){
        StringBuffer stringBuffer=new StringBuffer(ipPort);
        stringBuffer.append("/api/status_poll")
                .append("?phone=").append("18852951351")
                .append("&crawl_type=").append("jjd")
                .append("&uuid=").append("d91c7e547f5511e892d2000c29d380a8");
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(stringBuffer.toString(),String.class);
        log.info("responseEntity======:Body\n{}---StatusCode:{}---StatusCodeValue:{}"
                ,responseEntity.getBody(),responseEntity.getStatusCode(),responseEntity.getStatusCodeValue());

    }
    @Test
    public void testGetResult(){
        StringBuffer stringBuffer=new StringBuffer(ipPort);
        stringBuffer.append("/api/get_result")
                .append("?phone=").append("18852951351")
                .append("&crawl_type=").append("jjd")
                .append("&uuid=").append("d91c7e547f5511e892d2000c29d380a8");
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(stringBuffer.toString(),String.class);
        log.info("responseEntity======:Body\n{}---StatusCode:{}---StatusCodeValue:{}"
                ,responseEntity.getBody(),responseEntity.getStatusCode(),responseEntity.getStatusCodeValue());

    }

    public String getSignString(List<NameValuePair> list){
        StringBuffer signStringBuffer=new StringBuffer();
        for(int i=0;i<list.size();i++){
            signStringBuffer.append(list.get(i).getName()).append("=").append(list.get(i).getValue());
            if(i!=list.size()-1){
                signStringBuffer.append("&");
            }
        }
        log.info("······signStringBuffer:{}",signStringBuffer.toString());
        String signString="";
        try {
            signString = DigestUtils.md5Hex(signStringBuffer.toString().getBytes("UTF-8")).toUpperCase();
        } catch (UnsupportedEncodingException e) {
            log.error("对签名字符串转码出错，信息是:{}",e);
        }
        log.info("signString：{}",signString);
        return signString;
    }

    private List<NameValuePair> sortNameValuePair(List<NameValuePair> list){
        list.sort((o1,o2)->{
            int ascii1 = 0;
            int ascii2 = 0;
            int result=-1;
            for(int i = 0;i<o1.getName().length() && i<o2.getName().length();i++){
                ascii1=o1.getName().charAt(i);
                ascii2=o2.getName().charAt(i);
                if(ascii1 > ascii2){
                    result = 1;
                    break;
                }else if(ascii1 == ascii2){
                    continue;
                }else if(ascii1<ascii2){
                    result=-1;
                    break;
                }
            }
            return result;
        });
        return list;
    }

    @Test
    public void testVerifyCodeLogin(){
        String url = "http://192.168.2.69:"+6100+"/api/verifyCode/login";
        String reqJsonStr = "{\"header\":{\"service\":\"03\",\"inputCharset\":\"lihui\",\"requestDate\":\"20180515\",\"requestTime\":\"181809\",\"requestId\":\"89\"}},"+
                "{\"body\":{\"mobNo\":\"13687352585\",\"verifyCode\":\"344556\",\"smsVcDate\":\"20180515\",\"smsVcId\":\"3444\",\"channel\":\"00\"}}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(reqJsonStr,headers);
        ResponseEntity<String> responseEntity=restTemplate.postForEntity(url, entity,String.class);
        log.info("调用返回信息是：{}",responseEntity.getBody());
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
