package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.config;

import cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.verificationcodeprocessing.impl.ImageVerificationCodeGeneratorImpl;
import cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.verificationcodeprocessing.impl.SmsVerificationCodeGeneratorImpl;
import cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.verificationcodeprocessing.VerificationCodeGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 */
@Configuration
public class VerificationCodeConfig {
    @Bean(name = "imageVerificationCodeGenerator")
    @ConditionalOnMissingBean(name = "imageVerificationCodeI")
    public VerificationCodeGenerator imageVerificationCodeGenerator(){
        VerificationCodeGenerator verificationCodeGenerator =new ImageVerificationCodeGeneratorImpl();
        return verificationCodeGenerator;
    }
    @Bean(name = "smsVerificationCodeGenerator")
    @ConditionalOnMissingBean(name = "smsVerificationCodeI")
    public VerificationCodeGenerator smsVerificationCodeGenerator(){
        VerificationCodeGenerator verificationCodeGenerator =new SmsVerificationCodeGeneratorImpl();
        return verificationCodeGenerator;
    }
}
