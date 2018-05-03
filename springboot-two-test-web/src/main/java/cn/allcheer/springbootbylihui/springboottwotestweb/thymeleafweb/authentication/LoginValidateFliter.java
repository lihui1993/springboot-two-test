package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.authentication;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.model.LoginImageCode;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author lihui
 */
public class LoginValidateFliter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        LoginImageCode loginImageCode = (LoginImageCode) httpServletRequest.getSession().getAttribute("loginImageCode");
        String inputValidateCode = httpServletRequest.getParameter("volidatcode");
        if(httpServletRequest.getRequestURI().equals("/myauth/login")){
            if(checkTimeOut(loginImageCode) && checkValidateCode(loginImageCode,inputValidateCode)){
                filterChain.doFilter(httpServletRequest,httpServletResponse);
            }else{
                httpServletResponse.sendRedirect("/login?error");
            }
        }else {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }

    private boolean checkTimeOut(LoginImageCode loginImageCode){
        LocalDateTime localDateTimeNow=LocalDateTime.now();
        LocalDateTime localDateTime = loginImageCode.getExpireTime();
        if(localDateTimeNow.getNano() < localDateTime.getNano()){
            return true;
        }
        return false;
    }

    private boolean checkValidateCode(LoginImageCode loginImageCode,String inputValidateCode){
        if(StringUtils.hasText( inputValidateCode ) ){
            if(loginImageCode.getVolidatcode().equals(inputValidateCode)){
                return true;
            }
        }
        return false;
    }
}
