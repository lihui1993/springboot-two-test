package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.service.impl;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.dao.SysUser;
import cn.allcheer.springbootbylihui.springboottwotestdal.domain.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *自定义的认证用户信息，需要实现UserDetailsService接口
 * @author lihui 2017 12 26
 */
public class CustomUserServiceImpl implements UserDetailsService {
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
        //SysUser实现了UserDetails接口，可以直接返回
        return sysUser;
    }
}
