package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.config;

import cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.authentication.MyPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@EnableGlobalMethodSecurity
public class MyMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    private MyPermissionEvaluator myPermissionEvaluator;

    @Autowired
    public void setCustomPermissionEvaluator(MyPermissionEvaluator myPermissionEvaluator) {
        this.myPermissionEvaluator = myPermissionEvaluator;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler=new DefaultMethodSecurityExpressionHandler();
        defaultMethodSecurityExpressionHandler.setPermissionEvaluator(myPermissionEvaluator);
        return defaultMethodSecurityExpressionHandler;
//        return super.createExpressionHandler();
    }
}
