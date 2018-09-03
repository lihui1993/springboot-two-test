package cn.allcheer.springbootbylihui.springboottwotestdal.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * 图片验证码类
 * @author lihui
 */
@Getter
@Setter
public class LoginImageCode extends ValidateCode {
    private Image validateImage;
    public LoginImageCode(){}
    /**
     * 图片验证码的构造函数
     * @param validateImage 图片
     * @param validateCodeString 图片中验证码信息
     * @param expireTimeSecond 过期时间，以秒为单位
     */
    public LoginImageCode(Image validateImage, String validateCodeString, int expireTimeSecond) {
        super(validateCodeString,expireTimeSecond);
        this.validateImage = validateImage;
    }
}
