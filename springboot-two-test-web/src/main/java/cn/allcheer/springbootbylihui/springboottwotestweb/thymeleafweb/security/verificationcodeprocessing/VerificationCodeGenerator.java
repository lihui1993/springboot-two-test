package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.verificationcodeprocessing;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author lihui
 */
public interface VerificationCodeGenerator {
    /**
     *
     * @param type
     * @param servletWebRequest
     */
    void createSaveSendCode(String type, ServletWebRequest servletWebRequest);
}
