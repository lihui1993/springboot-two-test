package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.authentication;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.dao.SysUser;
import cn.allcheer.springbootbylihui.springboottwotestdal.domain.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
/**
 *自定义的认证用户信息，需要实现UserDetailsService接口
 * @author lihui 2017 12 26
 */
public class CustomUserServiceAuthentication implements UserDetailsService {
    @Autowired
    private SysUserRepository sysUserRepository;

    /**
     * 查找认证用户信息
     * @param username 用户姓名
     * @return 返回实现了UserDetails接口的通用用户
     * @throws UsernameNotFoundException 找不到用户时跑出的异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser=sysUserRepository.findByUserName(username);
        if(null==sysUser){
            throw new UsernameNotFoundException("用户未找到！！");
        }
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>(0);
        sysUser.getRoles().stream().forEach(sysRole -> grantedAuthorityList.add(new SimpleGrantedAuthority(sysRole.getRoleName())));
        //
        return new User(sysUser.getUserName(),sysUser.getPassWord(), grantedAuthorityList);
    }
}
