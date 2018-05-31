package cn.allcheer.springbootbylihui.springboottwotestdal.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.time.LocalDateTime;

/**
 * 图片验证码类
 * @author lihui
 */
@Getter
@Setter
public class LoginImageCode {
    private Image volidatImage;
    private String volidatcode;
    private LocalDateTime expireTime;

    /**
     * 图片验证码的构造函数
     * @param volidatImage 图片
     * @param volidatcode 图片中验证码信息
     * @param expireTimeSecond 过期时间，以秒为单位
     */
    public LoginImageCode(Image volidatImage, String volidatcode, int expireTimeSecond) {
        this.volidatImage = volidatImage;
        this.volidatcode = volidatcode;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTimeSecond);
    }
}
