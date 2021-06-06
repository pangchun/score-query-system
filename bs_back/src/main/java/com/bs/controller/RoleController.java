package com.bs.controller;

import com.alibaba.fastjson.JSON;
import com.bs.model.Permission;
import com.bs.model.Role;
import com.bs.service.RoleService;
import com.bs.support.common.CommonResult;
import com.bs.support.util.JsonUtil;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查询全部的角色信息
     */
    @PreAuthorize("hasAnyAuthority('wx:roe:selectAllRole')")
    @GetMapping("/selectAllRole")
    public CommonResult selectAllRole(@RequestParam(required = false) String data, @RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize){
        Role role=null;
        if (data!=null){
            role= JsonUtil.toObject(data, Role.class,String.class);
        }
        Page<Role> roles= roleService.selectAllRole(role,pageNum,pageSize);
        return CommonResult.success(roles,(long)roles.getTotal());
    }

    /**
     *新增角色
     */
    @PreAuthorize("hasAnyAuthority('wx:roe:addRole')")
    @PostMapping("/addRole")
    public String addRole(@RequestBody Role role){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用新增角色的方法
        if(roleService.addRole(role)>0){
            map.put("success",true);//成功
            map.put("message","新增成功");
        }else{
            map.put("success",false);//失败
            map.put("message","新增失败");
        }
        return JSON.toJSONString(map);
    }

    //修改角色
    @PostMapping("/edictRole")
    public String edictRole(@RequestBody Role role){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用修改角色的方法
        if(roleService.edictRole(role)>0){
            map.put("success",true);//成功
            map.put("message","修改成功");
        }else{
            map.put("success",false);//失败
            map.put("message","修改失败");
        }
        return JSON.toJSONString(map);
    }

    //删除角色
    @PostMapping("/deleteRoleById")
    public String deleteRoleById(@RequestBody Role role){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用删除的方法
        if(roleService.deleteRoleById(role)>0){
            map.put("success",true);//成功
            map.put("message","删除成功");
        }else{
            map.put("success",false);//失败
            map.put("message","删除失败");
        }
        return JSON.toJSONString(map);
    }

    //权限管理
    //查询全部的权限
    @PostMapping("/selectAllPermission")
    public List<Permission> selectAllPermission(){
        return roleService.selectAllPermission();
    }
    //查询某个角色拥有的权限
    @PostMapping("/selectRolePermission")
    public List<Permission> selectRolePermission(@RequestBody Role role){
        return roleService.selectRolePermission(role);
    }
    //给角色增加权限
    @PostMapping("/updateRolePermission")
    public String updateRolePermission(@RequestBody List<Role> role){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用角色增加权限的方法
        if(roleService.updateRolePermission(role)>0){
            //增加权限后需要增加角色权限的数量
            Role role1=new Role();
            role1.setId(role.get(0).getId());
            role1.setAdminCount(role.size());
            roleService.addCount(role1);
            map.put("success",true);//成功
            map.put("message","添加成功");
        }else{
            map.put("success",false);//失败
            map.put("message","添加失败");
        }
        return JSON.toJSONString(map);
    }
    //删除角色权限
    @PostMapping("/delRolePermission")
    public String delRolePermission(@RequestBody List<Role> role){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用删除角色权限的方法
        if(roleService.delRolePermission(role)>0){
            //删除权限后需要减少角色权限的数量
            Role role1=new Role();
            role1.setId(role.get(0).getId());
            role1.setAdminCount(-role.size());
            roleService.addCount(role1);
            map.put("success",true);//成功
            map.put("message","添加成功");
        }else{
            map.put("success",false);//失败
            map.put("message","添加失败");
        }
        return JSON.toJSONString(map);
    }

}
