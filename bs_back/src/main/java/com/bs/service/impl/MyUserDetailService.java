package com.bs.service.impl;

import com.bs.service.WxUserService;
import com.bs.support.vo.WxPermission;
import com.bs.support.vo.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;

@Service("userDetailsService")
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    WxUserService wxUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        WxUser user= wxUserService.getUserByName(username);
        List<WxPermission> permissionList= wxUserService.getPermissionsByUserId(user.getId());
        HashSet<WxPermission> permissions = new HashSet<>(permissionList);
        user.setAuthorities(permissions);
        return user;//到此返回的这个uer包含了用户的基本信息和一些相关权限
    }
}
