package com.bs.controller;

import com.alibaba.fastjson.JSON;
import com.bs.model.User;
import com.bs.service.WxUserService;
import com.bs.support.common.CommonResult;
import com.bs.support.vo.LoginParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController{

    @Value("${jwt.tokenHead}")
    String tokenHead;

    @Autowired
    WxUserService wxUserService;

    //注册的时候给密码加密
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    HttpServletRequest request;

    //登录
    @PostMapping("/in")
    public CommonResult login(@RequestBody LoginParams loginParams){
        HttpSession session = request.getSession(true);
        HashMap<String, String> data = new HashMap<>();
        String token = null;
        String code= (String) session.getAttribute("validationCode");
        //判断账户是否被冻结
        User user=wxUserService.isUseful(loginParams);
        //判断验证码是否正确
        if(!code.equals(loginParams.getCode())){
            return CommonResult.failed("验证码错误");
        }
        if(user==null){
            return CommonResult.validateFailed("用户名或密码错误");
        }else {
            if(user.getStatus()!=0){
                try {
                    token = wxUserService.login(loginParams);
                } catch (Exception e) {
                    e.printStackTrace();
                    return CommonResult.validateFailed("用户名或密码错误");
                }
                if (StringUtils.isEmpty(token)){
                    return CommonResult.validateFailed("用户名或密码错误");
                }
                data.put("user",loginParams.getUsername());
                data.put("tokenHead",tokenHead);
                data.put("access_token",token);
                return CommonResult.success(data);
            }else {
                return CommonResult.validateFailed("账户已被冻结");
            }
        }
    }


    //注册
    @PostMapping("/register")
    public String register(@RequestBody LoginParams loginParams){
        //将密码加密
        String passWord=loginParams.getPassword();
        loginParams.setPassword(passwordEncoder.encode(passWord));
        //查询是否有重复的账号
        Map<String,Object> map=new HashMap<String, Object>();
        if(wxUserService.judgeUserIsExist(loginParams)==null||wxUserService.judgeUserIsExist(loginParams).equals("")){
            //进行注册
            if(wxUserService.register(loginParams)>0){
                //注册的时候给用户一个普通用户的角色
                wxUserService.setRole(loginParams);
                map.put("success",false);
                map.put("message","注册成功");
            }else {
                map.put("success",false);
                map.put("message","注册失败");
            }
        }else {
            map.put("success",false);
            map.put("message","用户已存在");
        }
        return JSON.toJSONString(map);
    }
}

