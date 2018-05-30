package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *为了让springboot能托管在到dao模块的repository和pojo而做的配置
 * <p>@EnableJpaRepositories指定实现了spring data jpa 的repository的接口的包路径</p>
 *  <p>@EntityScan指定了ROM映射的实体的包路径</p>
 * @author lihui 2017 12 26
 */
//@Configuration
//@EnableJpaRepositories(basePackages = "cn.allcheer.springbootbylihui.springboottwotestdal.domain.repository")
//@EntityScan("cn.allcheer.springbootbylihui.springboottwotestdal.domain.dao")
public class DaoJpaRepositoryConfig {
}
