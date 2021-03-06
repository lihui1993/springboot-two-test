package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.controller;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.model.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lihui
 */
@Controller
@Slf4j
public class SimpleSecurityLoginController {

    @GetMapping("/")
    public String index(Model model){
        Msg msg=new Msg("测试标题","测试内容","额外信息，显示");
        model.addAttribute("msg",msg);
        log.debug("··········访问到了，给了model，返回index页面···");
        return "index";
    }
}
