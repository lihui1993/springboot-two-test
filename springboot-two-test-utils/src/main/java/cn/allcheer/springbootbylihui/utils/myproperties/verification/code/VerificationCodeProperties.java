package cn.allcheer.springbootbylihui.utils.myproperties.verification.code;

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
    private ImageVerificationCodeProperties imageVerificationCodeProperties;
    private SmsVerificationCodeProperties smsVerificationCodeProperties;
}
