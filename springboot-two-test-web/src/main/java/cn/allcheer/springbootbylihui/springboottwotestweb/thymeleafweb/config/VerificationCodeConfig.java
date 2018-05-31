package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.config;

import cn.allcheer.springbootbylihui.utils.verification.MyVerificationCodeI;
import cn.allcheer.springbootbylihui.utils.verification.impl.MyVerificationImageCodeImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 */
@Configuration
public class VerificationCodeConfig {
    @Bean
    @ConditionalOnMissingBean(name = "myVerificationCodeI")
    public MyVerificationCodeI myVerificationCodeI(){
        MyVerificationCodeI myVerificationCodeI=new MyVerificationImageCodeImpl();
        return myVerificationCodeI;
    }
}
