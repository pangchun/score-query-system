package com.bs.dao;

import com.bs.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HelloDao {
    List<User> hello(User user);
}
