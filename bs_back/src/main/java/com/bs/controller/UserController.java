package com.bs.controller;

import com.alibaba.fastjson.JSON;
import com.bs.model.Role;
import com.bs.model.User;
import com.bs.model.UserRoleRelation;
import com.bs.service.UserService;
import com.bs.support.common.CommonResult;
import com.bs.support.util.GetUserNameByToken;
import com.bs.support.util.JsonUtil;
import com.github.pagehelper.Page;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private GetUserNameByToken getUserNameByToken;

    /**
     * 分页查询全部用户
     * @param data 查询出来的全部用户数据
     * @param pageNum 第几页
     * @param pageSize 一页多少条数据
     * @return 按条件查询出来的数据
     */
    @PreAuthorize("hasAnyAuthority('wx:user:findAllUser')")
    @GetMapping("/findAllUser")
    public CommonResult findAllUser(@RequestParam(required = false) String data, @RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize){
        User user=null;
        if (data!=null){
            user= JsonUtil.toObject(data,User.class,String.class);
        }
        Page<User> users= userService.findAllUserRole(user,pageNum,pageSize);
        return CommonResult.success(users,(long)users.getTotal());
    }

    /**
     *查询全部角色，用于生成按角色查询用户的下拉框列表
     */
    @PostMapping("/findAllRole")
    public List findAllRole(){
        List<Role> list = new ArrayList<Role>();
        list=userService.findAllRole();
        return list;
    }

    /**
     * @param users 要解冻的用户
     */
    @PostMapping("/useful")
    @PreAuthorize("hasAnyAuthority('wx:user:useful')")
    public String useful(@RequestBody List<User> users){
        Map<String,Object> map=new HashMap<String, Object>();
        if(userService.useful(users)>0){
            map.put("success",true);
            map.put("message","修改成功");
        }else {
            map.put("success",false);
            map.put("message","修改失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * @param users 要冻结的用户
     */
    @PreAuthorize("hasAnyAuthority('wx:user:forbidden')")
    @PostMapping("/forbidden")
    //修改用户账户状态禁用
    public String forbidden(@RequestBody List<User> users){
        Map<String,Object> map=new HashMap<String, Object>();
        if(userService.forbidden(users)>0){
            map.put("success",true);
            map.put("message","修改成功");
        }else {
            map.put("success",false);
            map.put("message","修改失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * @param user 获取用户的账户
     * @return 用户详细信息
     */
    @PostMapping("/selectUserDetail")
    public CommonResult selectUserDetail(@RequestBody User user){
        User u=userService.selectUserDetail(user.getUsername());
        //密码不需要返回出去
        u.setPassword("");
        return CommonResult.success(u);
    }

    /**
     * @param user 用户信息
     * @return 修改后的用户信息
     */
    @PostMapping("/updateUser")
    public String updateUser(@RequestBody User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userService.updateUser(user) > 0) {
            map.put("success", true);
            map.put("message", "修改成功");
        } else {
            map.put("success", false);
            map.put("message", "修改失败");
        }

        return JSON.toJSONString(map);
    }

    /**
     * 根据id删除用户
     * @param user
     * @return
     */
    @PreAuthorize("hasAnyAuthority('wx:user:delete')")
    @PostMapping("/deleteUserById")
    @Transactional
    public String deleteUserById(@RequestBody User user){
        Map<String,Object> map = new HashMap<String,Object>();
        if(userService.deleteUserById(user.getId())>0){
            //删除用户角色关联表对应的关联
            userService.delUserRoleRelation(user);
            map.put("success",true);//成功
            map.put("message","删除成功");
        }else{
            map.put("success",false);//失败
            map.put("message","删除失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 查询用户所具有的角色id
     * @param userRoleRelation
     * @return
     */
    @PostMapping("/getUserRoles")
    public List<UserRoleRelation> getUserRoles(@RequestBody UserRoleRelation userRoleRelation){
        return userService.getUserRoles(userRoleRelation);
    }


    /**
     * 给用户添加角色：修改用户和角色之间的关联表（修改用户角色）
     * @param userRoleRelation
     * @return
     */
    @PreAuthorize("hasAnyAuthority('wx:user:userRole')")
    @PostMapping("/updateRole")
    @Transactional
    public String updateRole(@RequestBody UserRoleRelation userRoleRelation){
        Map<String,Object> map=new HashMap<String, Object>();
        //修改的角色不能为空
        if(userRoleRelation.getRoleIds().length!=0){
            //修改权限前，先删除角色
            if(userService.delUserRole(userRoleRelation)>0){
                //修改角色==删除成功后新增角色
                if(userService.updateRole(userRoleRelation)>0){
                    map.put("success",true);
                    map.put("message","修改成功");
                }else {
                    map.put("success",false);
                    map.put("message","修改失败");
                }
            }
        }else {
            map.put("success",false);
            map.put("message","请选择角色!");
        }

        return JSON.toJSONString(map);
    }

}
