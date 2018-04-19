package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.authentication;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lihui
 */
public class LoginValidateFliter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
