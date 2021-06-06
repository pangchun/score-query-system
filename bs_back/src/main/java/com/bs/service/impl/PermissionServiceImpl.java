package com.bs.service.impl;


import com.bs.dao.PermissionDao;
import com.bs.model.Permission;
import com.bs.service.PermissionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    /**
     *查询全部的权限
     */
    @Override
    public Page<Permission> findAllPermission(Permission permission, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,true);
        Page<Permission> permissions = permissionDao.findAllPermission(permission);
        return permissions;
    }

    /**
     * 新增权限
     */
    @Override
    public int addPermission(Permission permission) {
        return permissionDao.addPermission(permission);
    }

    /**
     *修改权限
     */
    @Override
    public int updatePermission(Permission permission) {
        return permissionDao.updatePermission(permission);
    }

    /**
     * 删除权限
     */
    //删除权限前需要判断角色是否拥有该角色，拥有就不能删除
    @Override
    public List<Permission> roleIsExistPermission(Permission permission) {
        return permissionDao.roleIsExistPermission(permission);
    }
    //删除权限
    @Override
    public int deletePermissionById(Permission permission) {
        return permissionDao.deletePermissionById(permission);
    }
}
