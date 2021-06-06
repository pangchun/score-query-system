package com.bs.service;


import com.bs.model.Role;
import com.bs.model.User;
import com.bs.model.UserRoleRelation;
import com.github.pagehelper.Page;
import com.sun.org.apache.bcel.internal.classfile.Code;

import java.util.List;

public interface UserService {

    Page<User> findAllUserRole(User user, Integer pageNum, Integer pageSize);

    List<Role> findAllRole();

    int useful(List<User> user);

    int forbidden(List<User> user);

    User selectUserDetail(String username);

    int updateUser(User user);

    int deleteUserById(Integer id);

    int delUserRoleRelation(User user);

    List<UserRoleRelation> getUserRoles(UserRoleRelation userRoleRelation);

    int delUserRole(UserRoleRelation userRoleRelation);

    int updateRole(UserRoleRelation userRoleRelation);



}