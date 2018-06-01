package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.authentication;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.dao.SysUser;
import cn.allcheer.springbootbylihui.springboottwotestdal.domain.repository.SysUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
/**
 *自定义的认证用户信息，需要实现UserDetailsService接口
 * @author lihui 2017 12 26
 */
@Slf4j
public class CustomUserServiceAuthentication implements UserDetailsService {
    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 查找认证用户信息
     * @param username 用户姓名
     * @return 返回实现了UserDetails接口的通用用户
     * @throws UsernameNotFoundException 找不到用户时跑出的异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("查找用户----username：{}",username);
        SysUser sysUser=sysUserRepository.findByUserName(username);
        if(null==sysUser){
            log.error("用户未找到");
//            解决spring security隐藏UsernameNotFoundException的问题
            throw new BadCredentialsException("用户未找到！！");
        }
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>(0);
        List<String> roleResource=new ArrayList<>(0);

        sysUser.getRoles().stream().forEach(sysRole -> {
            grantedAuthorityList.add(new SimpleGrantedAuthority(sysRole.getRoleName()));
            sysRole.getResources().stream().forEach(sysResource -> roleResource.add(sysResource.getResourceUrl()));
        });
        //
        MyCustomUser myCustomUser=new MyCustomUser(sysUser.getUserName(), passwordEncoder.encode(sysUser.getPassWord()), grantedAuthorityList);
        myCustomUser.setRoleResource(roleResource);
        return myCustomUser;
    }
}