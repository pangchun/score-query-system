package com.bs.service.impl;

import com.bs.dao.UserDao;
import com.bs.model.Role;
import com.bs.model.User;
import com.bs.model.UserRoleRelation;
import com.bs.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public Page<User> findAllUserRole(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,true);
        Page<User> users = userDao.findAllUserRole(user);
        return users;
    }

    @Override
    public List<Role> findAllRole() {
        return userDao.findAllRole();
    }

    @Override
    public int useful(List<User> user) {
        return userDao.useful(user);
    }

    @Override
    public int forbidden(List<User> user) {
        return userDao.forbidden(user);
    }

    @Override
    public User selectUserDetail(String username) {
        return userDao.selectUserDetail(username);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public int delUserRoleRelation(User user) {
        return userDao.delUserRoleRelation(user);
    }

    @Override
    public List<UserRoleRelation> getUserRoles(UserRoleRelation userRoleRelation) {
        return userDao.getUserRoles(userRoleRelation);
    }

    @Override
    public int delUserRole(UserRoleRelation userRoleRelation) {
        return userDao.delUserRole(userRoleRelation);
    }

    @Transactional
    @Override
    public int updateRole(UserRoleRelation userRoleRelation) {
        int result=0;
        for (int i = 0; i < userRoleRelation.getRoleIds().length; i++) {
            userRoleRelation.setRoleId(userRoleRelation.getRoleIds()[i]);
            result=userDao.updateRole(userRoleRelation);
            if(result<=0){
                return 0;
            }
        }
        return result;
    }
}