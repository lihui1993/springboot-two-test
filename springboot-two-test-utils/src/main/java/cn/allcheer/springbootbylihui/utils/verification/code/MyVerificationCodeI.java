package cn.allcheer.springbootbylihui.utils.verification.code;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.model.LoginImageCode;

/**
 * @author lihui
 */
public interface MyVerificationCodeI {
    /**
     * 产生图片验证码
     * @return 返回图片验证码类
     */
    LoginImageCode createVerificationCode();
}
