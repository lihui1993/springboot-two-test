package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.config;

import cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.service.impl.CustomUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.AbstractTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;

/**
 *关于spring security 的配置
 * @author lihui 2017 12 26
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 注册自定义的认证用户bean
     * @return 自定义的认证用户实现类
     */
    @Bean
    public CustomUserServiceImpl customUserService(){
        return new CustomUserServiceImpl();
    }
    /**
     * 将注册的自定义的认证用户加入到spring security
     * @param auth 认证管理者
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
    }

    /**
     * 配置访问的路径的权限
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
//                定制登录行为
                .and()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error")
                    .defaultSuccessUrl("/")
                    .permitAll()
//                定制注销行为
                .and()
                .logout()
//                注销后清除认证信息
                .clearAuthentication(true)
                .permitAll();
    }

    /**
     * 配置spring security web资源过滤器，将js和其他资源配置在这，spring security将不会拦截这些资源的加载
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //设置忽略哪些路径下的资源
        web.ignoring().antMatchers("/jQuery/**","/bootstrap/**");
    }
}
