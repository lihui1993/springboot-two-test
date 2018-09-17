package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.customproperties.codeproperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lihui
 */
@Setter
@Getter
@ToString
public class VerificationCodeProperties {
    private ImageVerificationCodeProperties imageVerificationCodeProperties=new ImageVerificationCodeProperties();
    private SmsVerificationCodeProperties smsVerificationCodeProperties=new SmsVerificationCodeProperties();
}
