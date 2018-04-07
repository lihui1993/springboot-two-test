package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.List;

@Configuration
public class MyPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        MyCustomUser myCustomUser= (MyCustomUser) authentication.getPrincipal();
        List<String> resources=myCustomUser.getRoleResource();
        if( resources.contains(targetDomainObject.toString()) ){
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
