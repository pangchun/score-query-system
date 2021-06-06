package com.bs.controller;

import com.alibaba.fastjson.JSON;
import com.bs.model.Permission;
import com.bs.service.PermissionService;
import com.bs.support.common.CommonResult;
import com.bs.support.util.JsonUtil;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PreAuthorize("hasAnyAuthority('wx:permission:selectAllPermission')")
    @GetMapping("/selectAllPermission")
    public CommonResult findAllPermission(@RequestParam(required = false) String data, @RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize){
        Permission permission=null;
        if (data!=null){
            permission= JsonUtil.toObject(data,Permission.class,String.class);
        }
        Page<Permission> permissions= permissionService.findAllPermission(permission,pageNum,pageSize);
        return CommonResult.success(permissions,(long)permissions.getTotal());
    }

    /**
     * 新增权限
     * @param permission
     * @return
     */
    @PreAuthorize("hasAnyAuthority('wx:permission:addPermission')")
    @PostMapping("/addPermission")
    public String addPermission(@RequestBody Permission permission){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用新增角色的方法
        if(permissionService.addPermission(permission)>0){
            map.put("success",true);//成功
            map.put("message","新增成功");
        }else{
            map.put("success",false);//失败
            map.put("message","新增失败");
        }
        return JSON.toJSONString(map);
    }

    //修改权限
    @PostMapping("/updatePermission")
    public String updatePermission(@RequestBody Permission permission){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用新增角色的方法
        if(permissionService.updatePermission(permission)>0){
            map.put("success",true);//成功
            map.put("message","修改成功");
        }else{
            map.put("success",false);//失败
            map.put("message","修改失败");
        }
        return JSON.toJSONString(map);
    }

    //删除权限
    @PostMapping("/deletePermissionById")
    public String deletePermissionById(@RequestBody Permission permission){
        Map<String,Object> map = new HashMap<String,Object>();
        //删除权限前需要判断角色是否拥有该角色，拥有就不能删除
        int exist=permissionService.roleIsExistPermission(permission).size();
        if(exist<=0){
            //调用删除权限的方法
            if(permissionService.deletePermissionById(permission)>0){
                map.put("success",true);//成功
                map.put("message","删除成功");
            }else{
                map.put("success",false);//失败
                map.put("message","删除失败");
            }
        }else {
            map.put("success",false);//失败
            map.put("message","权限使用中，删除失败！");
        }
        return JSON.toJSONString(map);
    }
}
