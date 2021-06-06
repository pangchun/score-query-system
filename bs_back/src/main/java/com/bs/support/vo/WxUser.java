package com.bs.support.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * UserDetails这个接口是我们自己用来定义用户表的结构的
 * SpringSecurity自己的用户信息只包含了Username，password，roles，
 * 假如我希望用户的实体类中还有性别sex字段，那么就没有办法了，
 * 所以SpringSecurity提供了UserDetails接口，我们可以自己新建一个包含sex字段的类，
 * 然后该类implements UserDetails接口，就可以获取我们说的这个sex字段
 *
 * UserDetails实例是通过UserDetailsService接口的loadUserByUsername方法返回的，
 * UserDetails接口中有一个getAuthorities方法，这个方法返回的是权限，
 * 但是我们返回的权限必须带有“ROLE_”开头才可以，spring会自己截取ROLE_后边的字符串，
 * 也就是说，比如：我的权限叫ADMIN，那么，我返回告诉spring security的时候，
 * 必须告诉他权限是ROLE_ADMIN，这样spring security才会认为权限是ADMIN
 */
public class WxUser  implements UserDetails {

    private Integer id;
    private String name;
    private String username;
    private String password;
    private String icon;
    private String email;
    private String phone;
    private String note;
    private Date createTime;
    private Date loginTime;
    private Integer status;

    private Set<? extends GrantedAuthority>  authorities;
    //获取权限
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    //获取密码
    @Override
    public String getPassword() {
        return this.password;
    }
    //获取账户
    @Override
    public String getUsername() {
        return this.username;
    }
    //账户是否没有过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //账户是否没有被锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //凭证（密码）没有过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //是否可用
    @Override
    public boolean isEnabled() {
        if(this.status==null){
            return false;
        }
        return this.status==1;
    }
    /**
     * 上面继承的方法不管是以get还是is开头，其实都是get方法
     * 有的时候在序列化的时候，要求有一个get方法，就必须有一个set方法，不然有可能会报错
     * 于是我们就给上面继承UserDetails时重写的方法，分别设置一个空的set方法，避免序列化出错。
     */
    public void setGetAuthorities(Collection<? extends GrantedAuthority> getAuthorities) {}
    public void setGetPassword(String getPassword) {}
    public void setGetUsername(String getUsername) {}
    public void setAccountNonExpired(boolean accountNonExpired) {}
    public void setAccountNonLocked(boolean accountNonLocked) {}
    public void setCredentialsNonExpired(boolean credentialsNonExpired) { }
    public void setEnabled(boolean enabled) {}
}
