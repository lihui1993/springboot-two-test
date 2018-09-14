package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.controller;

import cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.customproperties.CusConfigurationProperties;
import cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.verificationcodeprocessing.impl.VerificationCodeProcessingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lihui
 */
@Controller
@Slf4j
public class WriterLoginImageController {

    @Autowired
    private CusConfigurationProperties cusConfigurationProperties;
    @Autowired
    private VerificationCodeProcessingHandler verificationCodeProcessingHandler;


    @GetMapping("/getValidateCode/{type}")
    public void writerImageValidateCode(@PathVariable("type")String type, HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute(cusConfigurationProperties.getCusSecurityProperties().getWebSessionValidateCodeKey());
        verificationCodeProcessingHandler.finVerificationCodeGeneratorByType(type).createSaveSendCode(type,new ServletWebRequest(request,response));
    }
}
