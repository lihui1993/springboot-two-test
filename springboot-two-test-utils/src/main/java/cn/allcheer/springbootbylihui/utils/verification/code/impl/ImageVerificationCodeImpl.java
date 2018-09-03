package cn.allcheer.springbootbylihui.utils.verification.code.impl;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.model.LoginImageCode;
import cn.allcheer.springbootbylihui.utils.myproperties.CusConfigurationProperties;
import cn.allcheer.springbootbylihui.utils.verification.code.VerificationCodeI;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author lihui
 */
public class ImageVerificationCodeImpl implements VerificationCodeI {

    @Autowired
    private CusConfigurationProperties cusConfigurationProperties;
    @Override
    public LoginImageCode createVerificationCode() {
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
