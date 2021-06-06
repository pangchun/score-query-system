package com.bs.dao;

import com.bs.model.Permission;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PermissionDao {
    //查询全部的权限
    Page<Permission> findAllPermission(Permission permission);

    //新增权限
    int addPermission(Permission permission);

    //修改权限
    int updatePermission(Permission permission);

    //删除权限前需要判断角色是否拥有该角色，拥有就不能删除
    List<Permission> roleIsExistPermission(Permission permission);
    //删除权限
    int deletePermissionById(Permission permission);
}
