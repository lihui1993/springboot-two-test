package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.config;

import cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.authentication.CustomUserServiceAuthentication;
import cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.authentication.LoginValidateFilter;
import cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.authentication.MyPermissionEvaluator;
import cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.authentication.SecurityAuthSuccessHandler;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 *关于spring security 的配置
 * @author lihui 2017 12 26
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 注入自定义的认证成功后的处理方法类
     */
    @Autowired
    private SecurityAuthSuccessHandler securityAuthSuccessHandler;
    /**
     * 为了实现记住我功能
     */
    @Autowired
    private HikariDataSource dataSource;
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository=new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
    /**
     * 注入自定义的PermissionEvaluator
     */
    @Autowired
    private MyPermissionEvaluator myPermissionEvaluator;
    /**
     * 为了在页面上使用hasPermision(),增加自定义的PermissionEvaluator，使用默认的话，它总是返回false
     * @return
     */
    @Bean
    public DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler=new DefaultWebSecurityExpressionHandler();
        defaultWebSecurityExpressionHandler.setDefaultRolePrefix("ROLE_");
        defaultWebSecurityExpressionHandler.setTrustResolver(new AuthenticationTrustResolverImpl());
        defaultWebSecurityExpressionHandler.setPermissionEvaluator(myPermissionEvaluator);
        return defaultWebSecurityExpressionHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /**
     * 注册自定义的认证用户bean
     * @return 自定义的认证用户实现类
     */
    @Bean
    public CustomUserServiceAuthentication customUserService(){
        return new CustomUserServiceAuthentication();
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
        http
            .addFilterBefore(new LoginValidateFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
//          将重新定义过的WebSecurity表达式处理类告诉给HttpSecurity，这样最终在页面使用SpringSecurity方言的hasPermision()时才会有效
            .expressionHandler(defaultWebSecurityExpressionHandler())
            .antMatchers("/getValidateImageCode").permitAll()
//          任何请求都需要认证
            .anyRequest().authenticated()
            .and()
//          定制登录行为
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/myauth/login")
                .failureUrl("/login?error=ture")
//               登录认证成功后的处理方法类
                .successHandler(securityAuthSuccessHandler)
                .permitAll()
                .and()
//                添加记住我功能
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(300)
                .userDetailsService(customUserService())
                .and()
//          定制注销行为
            .logout()
//          注销后清除认证信息
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
