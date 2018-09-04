package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.config;

import cn.allcheer.springbootbylihui.verificationcode.VerificationCodeI;
import cn.allcheer.springbootbylihui.verificationcode.impl.ImageVerificationCodeImpl;
import cn.allcheer.springbootbylihui.verificationcode.impl.SmsVerificationCodeImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 */
@Configuration
public class VerificationCodeConfig {
    @Bean(name = "imageVerificationCodeImpl")
    @ConditionalOnMissingBean(name = "imageVerificationCodeI")
    public VerificationCodeI imageVerificationCodeI(){
        VerificationCodeI verificationCodeI =new ImageVerificationCodeImpl();
        return verificationCodeI;
    }
    @Bean(name = "smsVerificationCodeImpl")
    @ConditionalOnMissingBean(name = "smsVerificationCodeI")
    public VerificationCodeI smsVerificationCodeI(){
        VerificationCodeI verificationCodeI=new SmsVerificationCodeImpl();
        return verificationCodeI;
    }
}
