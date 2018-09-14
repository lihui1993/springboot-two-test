package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.verificationcodeprocessing.impl;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.model.LoginImageCode;
import cn.allcheer.springbootbylihui.springboottwotestdal.domain.model.ValidateCode;
import cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.customproperties.CusConfigurationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author lihui
 */
@Slf4j
public class ImageVerificationCodeGeneratorImpl extends AbstractVerificationCodeGenerator<LoginImageCode> {


    @Autowired
    private CusConfigurationProperties cusConfigurationProperties;
    @Override
    public LoginImageCode generatorVerificationCode() {
        int width= cusConfigurationProperties.getVerificationCodeProperties().getImageVerificationCodeProperties().getLoginImageCodeWidth();
        int height= cusConfigurationProperties.getVerificationCodeProperties().getImageVerificationCodeProperties().getLoginImageCodeHeight();
        BufferedImage bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics graphics=bufferedImage.getGraphics();
        Random random = new Random();

        graphics.setColor(getRandColor(200, 250));
        graphics.fillRect(0, 0, width, height);
        graphics.setFont(new Font("Times New Roman", Font.ITALIC, 40));
        graphics.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            graphics.drawLine(x, y, x + xl, y + yl);
        }

        StringBuffer sRand = new StringBuffer();


        for (int i = 0; i < cusConfigurationProperties.getVerificationCodeProperties().getImageVerificationCodeProperties().getLoginImageCodeLength();) {
            char c= (char) random.nextInt(122);
            boolean isNumber= c >= 48 && c <= 57;
            boolean isLowerCase= c >= 65 && c <= 90;
            boolean isUpperCase= c >= 97 && c <= 122;
            if( !( isNumber || isLowerCase || isUpperCase) ){
                continue;
            }
            sRand.append(c);
            graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            graphics.drawString(String.valueOf(c), cusConfigurationProperties.getVerificationCodeProperties().getImageVerificationCodeProperties().getLoginImageCodeXBaseCoordinate() * i + 36, cusConfigurationProperties.getVerificationCodeProperties().getImageVerificationCodeProperties().getLoginImageCodeYCoordinate());
            ++i;
        }

        graphics.dispose();
        return  new LoginImageCode(bufferedImage,sRand.toString(), cusConfigurationProperties.getVerificationCodeProperties().getImageVerificationCodeProperties().getLoginImageCodeExpireTimeSecond());
    }

    @Override
    protected void saveCode(LoginImageCode validateCode, ServletWebRequest servletWebRequest) {
        ValidateCode validateCode1S=new ValidateCode();
        validateCode1S.setValidateCodeString(validateCode.getValidateCodeString());
        validateCode1S.setExpireTime(validateCode.getExpireTime());
        servletWebRequest.getRequest().getSession().setAttribute(cusConfigurationProperties.getCusSecurityProperties().getWebSessionValidateCodeKey(),validateCode1S);
    }

    @Override
    protected void sendCode(LoginImageCode validateCode, ServletWebRequest servletWebRequest) {
        try {
            servletWebRequest.getResponse().setContentType(MediaType.IMAGE_JPEG_VALUE);
            ImageIO.write((RenderedImage) validateCode.getValidateImage(),"jpeg",servletWebRequest.getResponse().getOutputStream());
        } catch (IOException e) {
            log.error("将图片验证码输出到浏览器出现错误，信息是："+e);
        }
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
