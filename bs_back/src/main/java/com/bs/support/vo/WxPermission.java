package com.bs.support.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 *  GrantedAuthority是一个接口，
 *  其中只定义了一个getAuthority()方法，其返回值为String类型。
 *  该方法允许AccessDecisionManager获取一个能够精确代表该权限的字符串。
 *  通过返回一个字符串，一个GrantedAuthority能够很轻易的被大部分AccessDecisionManager读取。
 *  如果一个GrantedAuthority不能够精确的使用一个String来表示，那么其对应的getAuthority()方法调用应当返回一个null，
 *  这表示AccessDecisionManager必须对该GrantedAuthority的实现有特定的支持，
 *  从而可以获取该GrantedAuthority所代表的权限信息。
 */
public class WxPermission  implements GrantedAuthority{
    private Integer id;
    private Integer pid;
    private String name;
    private String value;
    private String icon;
    private Integer type;
    private String uri;
    private Integer status;
    private Date createTime;
    private String sort;

    @Override
    public String getAuthority() {
        return this.value;
    }
}

