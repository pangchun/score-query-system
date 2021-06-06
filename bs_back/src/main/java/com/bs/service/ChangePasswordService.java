package com.bs.service;

import com.bs.model.User;
import com.bs.support.vo.LoginParams;

import java.util.List;

public interface ChangePasswordService {
        String findPasswordByUsername(String PutUsername);
        boolean matchOldNewPass(String PutUsername,String PutNewPassword);
        int changePassword(LoginParams loginParams);
}
