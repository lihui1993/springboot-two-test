package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.authentication;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
class MyCustomUser extends User {

    private List<String> roleResource;

    public MyCustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
