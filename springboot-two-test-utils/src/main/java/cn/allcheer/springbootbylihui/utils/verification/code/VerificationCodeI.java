package cn.allcheer.springbootbylihui.utils.verification.code;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.model.ValidateCode;

/**
 * @author lihui
 */
public interface VerificationCodeI {
    /**
     * 产生图片验证码
     * @return 返回图片验证码类
     */
    ValidateCode createVerificationCode();
}
