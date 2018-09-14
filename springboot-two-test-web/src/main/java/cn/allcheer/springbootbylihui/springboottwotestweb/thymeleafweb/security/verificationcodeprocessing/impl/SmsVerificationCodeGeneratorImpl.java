package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.verificationcodeprocessing.impl;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.model.ValidateCode;
import cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.customproperties.CusConfigurationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Random;

/**
 * @author lihui
 */
@Slf4j
public class SmsVerificationCodeGeneratorImpl extends AbstractVerificationCodeGenerator<ValidateCode> {

    @Autowired
    private CusConfigurationProperties cusConfigurationProperties;

    @Override
    public ValidateCode generatorVerificationCode() {
        Random random=new Random();
        StringBuffer sRandom=new StringBuffer();
        for (int i=0;i<cusConfigurationProperties.getVerificationCodeProperties().getSmsVerificationCodeProperties().getSmsCodeLength();){
            char c= (char) random.nextInt(57);
            boolean isNumber= c<=48 && c>=57;
            if(!isNumber){
                continue;
            }
            sRandom.append(c);
            i++;
        }
        return new ValidateCode(sRandom.toString(),cusConfigurationProperties.getVerificationCodeProperties().getSmsVerificationCodeProperties().getSmsCodeExpireTimeSeconds());
    }

    @Override
    protected void saveCode(ValidateCode validateCode, ServletWebRequest servletWebRequest) {
        servletWebRequest.getRequest().getSession().setAttribute(cusConfigurationProperties.getCusSecurityProperties().getWebSessionValidateCodeKey(),validateCode);
    }

    @Override
    protected void sendCode(ValidateCode validateCode, ServletWebRequest servletWebRequest) {
        log.info("发送了短信验证码："+validateCode.getValidateCodeString());
    }


}
