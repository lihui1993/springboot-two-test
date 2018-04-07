package cn.allcheer.springbootbylihui.springboottwotestdal.domain.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
public class SysResource {
    @Id
    @GeneratedValue
    private Integer id;
    private String resourceName;
    private String resourceUrl;
}
