package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.controller;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.model.LoginImageCode;
import cn.allcheer.springbootbylihui.verificationcode.VerificationCodeI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @author lihui
 */
@Controller
@Slf4j
public class WriterLoginImageController {

    @Autowired
    private Map<String,VerificationCodeI> verificationCodeIHashMap;


    @GetMapping("/getValidateCode/{type}")
    public void writerImageValidateCode(@PathVariable("type")String type, ServletWebRequest servletWebRequest) throws IOException {
        log.info("请求到了图片验证码生成的headler");
        servletWebRequest.getRequest().getSession().removeAttribute("loginVerificationImageCode");
        LoginImageCode loginImageCode= (LoginImageCode) verificationCodeIHashMap.get(type+"VerificationCodeImpl").createVerificationCode();
        servletWebRequest.getRequest().getSession().setAttribute("loginVerificationImageCode",loginImageCode);
        servletWebRequest.getResponse().setContentType(MediaType.IMAGE_JPEG_VALUE);
        ImageIO.write((RenderedImage) loginImageCode.getValidateImage(),"jpeg",servletWebRequest.getResponse().getOutputStream());
        log.info("请求图片验证码生成结束");
    }
}
