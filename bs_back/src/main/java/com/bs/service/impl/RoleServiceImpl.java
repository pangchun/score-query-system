package com.bs.service.impl;


import com.bs.dao.RoleDao;
import com.bs.model.Permission;
import com.bs.model.Role;
import com.bs.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    /**
     *查询全部角色
     */
    @Override
    public Page<Role> selectAllRole(Role role, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,true);
        Page<Role> roles = roleDao.selectAllRole(role);
        return roles;
    }

    /**
     * 新增角色
     */
    @Override
    public int addRole(Role role) {
        return roleDao.addRole(role);
    }

    /**
     * 修改角色
     */
    @Override
    public int edictRole(Role role) {
        return roleDao.edictRole(role);
    }

    /**
     *删除角色
     */
    @Override
    public int deleteRoleById(Role role) {
        return roleDao.deleteRoleById(role);
    }

    /**
     * 分配权限
     */
    //查询某个角色的全部权限
    @Override
    public List<Permission> selectAllPermission() {
        return roleDao.selectAllPermission();
    }
    //查询某个角色拥有的权限
    @Override
    public List<Permission> selectRolePermission(Role role) {
        return roleDao.selectRolePermission(role);
    }
    //给角色增加权限
    @Override
    public int updateRolePermission(List<Role> role) {
        return roleDao.updateRolePermission(role);
    }
    //增加权限后需要增加角色权限的数量
    @Override
    public void addCount(Role role1) {
        roleDao.addCount(role1);
    }
    //删除角色权限
    @Override
    public int delRolePermission(List<Role> role) {
        return roleDao.delRolePermission(role);
    }


}
