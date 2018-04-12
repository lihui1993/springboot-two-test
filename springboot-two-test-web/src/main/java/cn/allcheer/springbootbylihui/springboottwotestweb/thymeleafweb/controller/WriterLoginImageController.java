package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.controller;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.model.LoginImageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Controller
@Slf4j
public class WriterLoginImageController {


    @GetMapping("/volidatGetCode")
    public void writerImageVolidataCode(HttpServletRequest request, HttpServletResponse response){
        LoginImageCode loginImageCode=createVolidataImageCode();


    }

    private LoginImageCode createVolidataImageCode() {
        int width=200;
        int height=50;
        BufferedImage bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics graphics=bufferedImage.getGraphics();
        Random random = new Random();

        graphics.setColor(getRandColor(200, 250));
        graphics.fillRect(0, 0, width, height);
        graphics.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        graphics.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            graphics.drawLine(x, y, x + xl, y + yl);
        }

        StringBuffer sRand = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            sRand.append(random.nextInt(10));
            graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            graphics.drawString(String.valueOf( random.nextInt(10) ), 13 * i + 6, 16);
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
