package com.bs.service.impl;

import com.bs.dao.HelloDao;
import com.bs.model.User;
import com.bs.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HelloServiceImpl implements HelloService {
    @Autowired
    HelloDao helloDao;

    @Override
    public List<User> hello(User user) {

        return helloDao.hello(user);
    }
}
