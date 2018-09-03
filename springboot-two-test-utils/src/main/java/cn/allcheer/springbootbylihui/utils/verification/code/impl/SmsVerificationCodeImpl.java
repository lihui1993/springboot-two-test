package cn.allcheer.springbootbylihui.utils.verification.code.impl;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.model.ValidateCode;
import cn.allcheer.springbootbylihui.utils.myproperties.CusConfigurationProperties;
import cn.allcheer.springbootbylihui.utils.verification.code.VerificationCodeI;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

/**
 * @author lihui
 */
public class SmsVerificationCodeImpl implements VerificationCodeI {

    @Autowired
    private CusConfigurationProperties cusConfigurationProperties;

    @Override
    public ValidateCode createVerificationCode() {
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
}
