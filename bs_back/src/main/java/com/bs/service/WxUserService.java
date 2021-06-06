package com.bs.service;


import com.bs.model.User;
import com.bs.support.vo.LoginParams;
import com.bs.support.vo.WxPermission;
import com.bs.support.vo.WxUser;

import java.util.List;

public interface WxUserService {
    WxUser getUserByName(String name);
    List<WxPermission> getPermissionsByUserId(Integer id);

    String login(LoginParams loginParams);

    /**
     * 注册
     * @param loginParams
     * @return
     */
    //注册的时候判断用户是否已经存在
    LoginParams judgeUserIsExist(LoginParams loginParams);
    //注册
    int register(LoginParams loginParams);
    //注册的时候给用户一个普通用户的角色
    void setRole(LoginParams loginParams);

    User isUseful(LoginParams loginParams);
}

