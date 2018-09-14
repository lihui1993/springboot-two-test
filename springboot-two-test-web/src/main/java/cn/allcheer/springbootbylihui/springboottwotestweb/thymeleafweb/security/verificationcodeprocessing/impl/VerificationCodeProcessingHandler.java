package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.verificationcodeprocessing.impl;

import cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.verificationcodeprocessing.VerificationCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lihui
 */
@Component
public class VerificationCodeProcessingHandler {

    @Autowired
    private Map<String,VerificationCodeGenerator> verificationCodeIHashMap;

    public VerificationCodeGenerator finVerificationCodeGeneratorByType(String type){
        return verificationCodeIHashMap.get(type+VerificationCodeGenerator.class.getSimpleName());
    }
}
