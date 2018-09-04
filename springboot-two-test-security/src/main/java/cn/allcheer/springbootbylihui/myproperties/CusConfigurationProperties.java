package cn.allcheer.springbootbylihui.myproperties;

import cn.allcheer.springbootbylihui.myproperties.securityproperties.CusSecurityProperties;
import cn.allcheer.springbootbylihui.myproperties.verification.code.VerificationCodeProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author lihui
 */
@ConfigurationProperties(prefix = "my.config")
@PropertySource(value = "customApplication.properties")
@Component
@Setter
@Getter
@ToString
public class CusConfigurationProperties {
   private VerificationCodeProperties verificationCodeProperties;
   private CusSecurityProperties cusSecurityProperties;
}
