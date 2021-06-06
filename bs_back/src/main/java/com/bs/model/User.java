package com.bs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    private Integer id;
    private String username;
    private String realName;
    private String password;
    private String sex;
    private String icon;//头像
    private String note;//备注信息
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date loginTime;//最后拿的登陆时间
    private Integer status;//状态
    private String email;
    private String phone;

    //角色表
    private String role;

}
