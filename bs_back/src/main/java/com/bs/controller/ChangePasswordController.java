package com.bs.controller;

import com.bs.model.User;
import com.bs.service.ChangePasswordService;
import com.bs.support.common.CommonResult;
import com.bs.support.vo.LoginParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/changePassword")
public class ChangePasswordController {
    @Autowired
    ChangePasswordService changePasswordService;
    @PostMapping("/AcceptUserInfo")
    public CommonResult changePassword(@RequestBody LoginParams loginParams){
         Map<String,Object> map=new HashMap<String, Object>();
         int flag = changePasswordService.changePassword(loginParams);
         if (flag==0){
             map.put("success",false);
             map.put("message","原始密码输入错误，请输入正确的密码。");
         }else if(flag==1){
             map.put("success",true);
             map.put("message","成功修改密码。");
         }else{
             map.put("success",false);
             map.put("message","修改密码失败。");
         }
         return CommonResult.success(map);
    }

}
