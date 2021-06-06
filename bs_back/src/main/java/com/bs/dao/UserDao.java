package com.bs.dao;

import com.bs.model.Role;
import com.bs.model.User;
import com.bs.model.UserRoleRelation;
import com.github.pagehelper.Page;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    Page<User> findAllUserRole(User user);

    List<Role> findAllRole();

    int useful(List<User> user);

    int forbidden(List<User> user);

    User selectUserDetail(@Param("username") String username);

    int updateUser(User user);

    int deleteUserById(Integer id);

    int delUserRoleRelation(User user);

    List<UserRoleRelation> getUserRoles(UserRoleRelation userRoleRelation);

    int delUserRole(UserRoleRelation userRoleRelation);

    int updateRole(UserRoleRelation userRoleRelation);


}
