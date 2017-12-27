package cn.allcheer.springbootbylihui.springboottwotestdal.domain.repository;

import cn.allcheer.springbootbylihui.springboottwotestdal.domain.dao.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lihui 2017 12 26
 */
@Repository()
public interface SysUserRepository extends JpaRepository<SysUser,Long> {
    /**
     * 按用户名查找用户
     * @param userName 用户名
     * @return 用户对象
     */
    SysUser findByUserName(String userName);
}
