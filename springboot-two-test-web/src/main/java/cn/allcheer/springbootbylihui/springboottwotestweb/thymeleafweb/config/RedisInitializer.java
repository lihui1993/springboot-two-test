package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * @author lihui
 */
public class RedisInitializer extends AbstractHttpSessionApplicationInitializer {
    public RedisInitializer(){
        super(LettuceConnectionFactoryConfig.class);
    }
}
