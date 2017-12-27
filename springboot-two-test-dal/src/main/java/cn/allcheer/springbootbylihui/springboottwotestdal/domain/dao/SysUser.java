package cn.allcheer.springbootbylihui.springboottwotestdal.domain.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lihui
 */
@Entity
@Getter
@Setter
@ToString
public class SysUser implements UserDetails {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String passWord;
    /**
     * 配置多对多关系
     * 对应权限表
     */
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<SysRole> roles;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities =new ArrayList<>();
        List<SysRole> roles=this.getRoles();
        for (SysRole sysRole:roles){
            authorities.add(new SimpleGrantedAuthority(sysRole.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.passWord;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
