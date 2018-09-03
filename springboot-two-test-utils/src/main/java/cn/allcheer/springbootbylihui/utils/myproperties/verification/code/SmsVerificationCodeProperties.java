package cn.allcheer.springbootbylihui.utils.myproperties.verification.code;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lihui
 */
@Getter
@Setter
@ToString
public class SmsVerificationCodeProperties {
    private int smsCodeExpireTimeSeconds=180;
    private int smsCodeLength=6;
}
