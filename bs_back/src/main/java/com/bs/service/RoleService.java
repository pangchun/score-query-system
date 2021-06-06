package com.bs.service;


import com.bs.model.Permission;
import com.bs.model.Role;
import com.github.pagehelper.Page;

import java.util.List;

public interface RoleService {
    //查询全部角色
    Page<Role> selectAllRole(Role role, Integer pageNum, Integer pageSize);

    //删除角色
    int addRole(Role role);

    //修改角色
    int edictRole(Role role);

    //删除角色
    int deleteRoleById(Role role);

    //查询某个角色全部的权限
    List<Permission> selectAllPermission();
    //查询某个角色拥有的权限
    List<Permission> selectRolePermission(Role role);
    //给角色增加权限
    int updateRolePermission(List<Role> role);
    //增加权限后需要增加角色权限的数量
    void addCount(Role role1);
    //删除角色权限
    int delRolePermission(List<Role> role);

}
