package com.bs.dao;

import com.bs.model.User;
import com.bs.support.vo.LoginParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChangePasswordDao {
    String findPasswordByUsername(String username);
    int changeUserPassword(LoginParams loginParams);
    //@Param("username")
    //@Param("password")
}