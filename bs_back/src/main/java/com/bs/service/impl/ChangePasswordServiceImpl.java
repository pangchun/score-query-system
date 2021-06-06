package com.bs.service.impl;

import com.bs.dao.ChangePasswordDao;
import com.bs.model.User;
import com.bs.service.ChangePasswordService;
import com.bs.support.vo.LoginParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChangePasswordServiceImpl implements ChangePasswordService {
    @Autowired
    ChangePasswordDao userChangePasswordDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired ChangePasswordDao changePasswordDao;
    @Override
    //通过用户名到数据库找密码
    public String findPasswordByUsername(String PutUsername) {
        return userChangePasswordDao.findPasswordByUsername(PutUsername);
    }
    //新旧密码匹配
    public boolean matchOldNewPass(String PutOldPassword,String PutUsername){
        //String encrptedPassword=findPasswordByUsername(PutUsername);
        return passwordEncoder.matches(PutOldPassword,findPasswordByUsername(PutUsername));
    }
    //修改密码
    public int changePassword(LoginParams loginParams){
        if (!matchOldNewPass(loginParams.getOriginalPassword(),loginParams.getUsername())){
                return 0;
        }else{
            loginParams.setNewPassword(passwordEncoder.encode(loginParams.getNewPassword()));
           int flag = changePasswordDao.changeUserPassword(loginParams);
           if (flag==1){
               return 1;
           }else {
               return 2;
           }
        }
    }
}
