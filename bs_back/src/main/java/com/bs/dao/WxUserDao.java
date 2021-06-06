package com.bs.dao;

import com.bs.model.User;
import com.bs.support.vo.LoginParams;
import com.bs.support.vo.WxPermission;
import com.bs.support.vo.WxUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
/**
 * @Repository注解:它用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean
 * 不写的话在service层自动注入的时候会报错
 * 所报的错不影响程序运行，但是建议还是加上该注解，避免造成误会
 */
public interface WxUserDao {
    List<WxUser> getUserByName(String name);
    List<WxPermission> getPermisionByUserId(Integer userId);

    /**
     * 注册
     * @param loginParams
     * @return
     */
    //注册的时候判断用户是否已经存在
    LoginParams judgeUserIsExist(LoginParams loginParams);
    //注册
    int register(LoginParams loginParams);
    //注册的时候给用户一个普通用户的角色
    void setRole(LoginParams loginParams);

    User isUseful(LoginParams loginParams);
}
