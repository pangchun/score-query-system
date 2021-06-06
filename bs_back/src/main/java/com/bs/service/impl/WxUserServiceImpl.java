package com.bs.service.impl;

import cn.hutool.core.lang.Assert;
import com.bs.dao.WxUserDao;
import com.bs.model.User;
import com.bs.service.WxUserService;
import com.bs.support.util.JwtTokenUtil;
import com.bs.support.vo.LoginParams;
import com.bs.support.vo.WxPermission;
import com.bs.support.vo.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class WxUserServiceImpl implements WxUserService {
    @Autowired
    WxUserDao wxUserDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @Override
    public WxUser getUserByName(String name) {
        List<WxUser> users= wxUserDao.getUserByName(name);
        /**
         * Assert.isTrue 是一个断言函数：用于验证指定的条件是否为 true。
         * 如果该条件为 false，则断言失败。如果断言失败，将显示一则消息。
         */
        Assert.isTrue(users.size()==1,"您输入的账户不存在，或者有多个相同的账户");
        return users.get(0);
    }

    @Override
    public List<WxPermission> getPermissionsByUserId(Integer id) {
        return wxUserDao.getPermisionByUserId(id);
    }

    @Override
    public String login(LoginParams loginParams) {
        String username = loginParams.getUsername();
        Assert.notNull(username,"账号必须不能为空");
        String password = loginParams.getPassword();
        Assert.notNull(password,"密码必须不能为空");
        WxUser userByName = getUserByName(username);
        boolean matches = passwordEncoder.matches(password, userByName.getPassword());
        if(matches){
            return jwtTokenUtil.generateToken(userByName);
        }
        return null;
    }

    /**
     * 注册
     * @param loginParams
     * @return
     */
    //注册的时候判断用户是否已经存在
    @Override
    public LoginParams judgeUserIsExist(LoginParams loginParams) {
        return wxUserDao.judgeUserIsExist(loginParams);
    }
    //注册
    @Override
    public int register(LoginParams loginParams) {
        return wxUserDao.register(loginParams);
    }
    //注册的时候给用户一个普通用户的角色
    @Override
    public void setRole(LoginParams loginParams) {
        wxUserDao.setRole(loginParams);
    }

    @Override
    public User isUseful(LoginParams loginParams) {
        return wxUserDao.isUseful(loginParams);
    }
}

