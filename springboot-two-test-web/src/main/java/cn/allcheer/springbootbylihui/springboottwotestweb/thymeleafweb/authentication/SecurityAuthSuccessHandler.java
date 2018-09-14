package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.authentication;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.model.SimpleResponse;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lihui
 */
@Component
public class SecurityAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        SimpleResponse simpleResponse=new SimpleResponse();
        simpleResponse.setState(HttpStatus.OK.value());
        simpleResponse.setMsg("登录成功");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        response.getWriter().write(JSONObject.toJSONString(simpleResponse));
        response.getWriter().close();
    }
}
