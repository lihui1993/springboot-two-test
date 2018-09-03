package cn.allcheer.springbootbylihui.springboottwotestdal.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author lihui
 */
@Getter
@Setter
@ToString
public class ValidateCode {
    private String validateCodeString;
    private LocalDateTime expireTime;

    public ValidateCode(){}
    public ValidateCode(String validateCodeString,int expireTime){
        this.validateCodeString=validateCodeString;
        this.expireTime=LocalDateTime.now().plusSeconds(expireTime);
    }
}
