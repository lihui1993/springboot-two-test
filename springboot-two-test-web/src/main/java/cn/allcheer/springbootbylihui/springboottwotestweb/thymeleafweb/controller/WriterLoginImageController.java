package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.controller;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.model.LoginImageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Random;

@Controller
@Slf4j
public class WriterLoginImageController {


    @RequestMapping("/volidatGetCode")
    public void writerImageVolidataCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("请求到了图片验证码生成的headler");
        LoginImageCode loginImageCode=createVolidataImageCode();
        request.getSession().setAttribute("loginImageCode",loginImageCode);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        ImageIO.write((RenderedImage) loginImageCode.getVolidatImage(),"jpeg",response.getOutputStream());
        log.info("请求图片验证码生成结束");
    }

    private LoginImageCode createVolidataImageCode() {
        int width=200;
        int height=50;
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

        for (int i = 0; i < 4;) {
            char c= (char) random.nextInt(122);
            if(sRand.length()==4){
                break;
            }
            if( !((c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122 )) ){
                continue;
            }
            log.info("c is :{}",String.valueOf(c));
            if(StringUtils.hasText( String.valueOf(c).trim() )){
                sRand.append(c);
                graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
                graphics.drawString(String.valueOf(c), 43 * i + 36, 36);
                ++i;
            }
        }

        graphics.dispose();
        return  new LoginImageCode(bufferedImage,sRand.toString(),120);
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
