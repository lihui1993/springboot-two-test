package cn.allcheer.springbootbylihui.springboottwotestdal.domain.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class SysRole {
    /**
     * 角色id
     */
    @Id
    @GeneratedValue
    private long id;
    /**
     * 角色名称
     */
    private String name;
}
