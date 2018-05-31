package cn.allcheer.springbootbylihui.utils.myproperties;

import cn.allcheer.springbootbylihui.utils.myproperties.verification.code.MyVerificationCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lihui
 */
@ConfigurationProperties(prefix = "my.configuration")
@Component
@Setter
@Getter
@ToString
public class MyConfigurationProperties {
   private MyVerificationCode myVerificationCode;
}
