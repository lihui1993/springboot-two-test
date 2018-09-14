package cn.allcheer.springbootbylihui.springboottwotestweb.thymeleafweb.security.customproperties.securityproperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lihui
 */
@Setter
@Getter
@ToString
public class CusSecurityProperties {
    private String loginPage="/login";
    private String loginProcessingUrl="/myAuth/login";
    private String getValidateCodeUrl="/getValidateCode/*";
    private int tokenValiditySeconds =60*60*24;
    private String webSecurityExpressionHandlerRolePrefix="";
    private String webSessionValidateCodeKey="loginVerificationCode";
}
