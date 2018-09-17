package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.customproperties;

import cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.customproperties.codeproperties.VerificationCodeProperties;
import cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.customproperties.securityproperties.CusSecurityProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lihui
 */
@ConfigurationProperties(prefix = "my.config")
@Component
@Setter
@Getter
@ToString
public class CusConfigurationProperties {
   private VerificationCodeProperties verificationCodeProperties=new VerificationCodeProperties();
   private CusSecurityProperties cusSecurityProperties=new CusSecurityProperties();
}
