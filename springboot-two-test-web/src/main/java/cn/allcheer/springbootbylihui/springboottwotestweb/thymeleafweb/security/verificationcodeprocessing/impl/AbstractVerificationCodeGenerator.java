package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.verificationcodeprocessing.impl;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.model.ValidateCode;
import cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.verificationcodeprocessing.VerificationCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author lihui
 */
public abstract class AbstractVerificationCodeGenerator<T extends ValidateCode> implements VerificationCodeGenerator {
    /**
     * 产生验证码,具体产生什么类型的验证码由子类实现
     * @return 返回验证码类
     */
    protected abstract T generatorVerificationCode();

    /**
     * 保存验证码
     * @param validateCode 生成的验证码
     * @param servletWebRequest
     */
    protected abstract void saveCode(T validateCode,ServletWebRequest servletWebRequest);

    /**
     * 发送验证码
     * @param validateCode 生成的验证码
     * @param servletWebRequest
     */
    protected abstract void sendCode(T validateCode, ServletWebRequest servletWebRequest);

    @Override
    public void createSaveSendCode(String type, ServletWebRequest servletWebRequest){
        T validateCode=generatorVerificationCode();
        saveCode(validateCode,servletWebRequest);
        sendCode(validateCode,servletWebRequest);
    }
}
