package cn.allcherr.springboot.test;

import cn.allcheer.springbootbylihui.springboottwotestweb.SpringbootTwoTestWebApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootTwoTestWebApplication.class)
@Slf4j
public class RestfulWebTest {
    private TestRestTemplate restTemplate=new TestRestTemplate();
    @Test
    public void test1(){
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



}
