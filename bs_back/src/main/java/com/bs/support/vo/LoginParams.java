package com.bs.support.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 设置登录的参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginParams implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String code;
    private String originalPassword;
    private String newPassword;
}