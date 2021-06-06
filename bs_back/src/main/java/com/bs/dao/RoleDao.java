package com.bs.dao;

import com.bs.model.Permission;
import com.bs.model.Role;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleDao {
    //查询全部角色信息
    Page<Role> selectAllRole(Role role);

    //新增角色
    int addRole(Role role);

    //修改角色
    int edictRole(Role role);

    //删除角色
    int deleteRoleById(Role role);

    //查询某个角色的全部权限
    List<Permission> selectAllPermission();
    //查询角色拥有的权限
    List<Permission> selectRolePermission(Role role);
    //给角色增加权限
    int updateRolePermission(List<Role> role);
    //增加权限后需要修改角色的权限数量
    void addCount(Role role1);
    //删除角色权限
    int delRolePermission(List<Role> role);
}
