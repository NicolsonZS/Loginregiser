package com.loginregister.demo.service;


import com.loginregister.demo.dao.UserMapper;
import com.loginregister.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLoginService {

    @Autowired
    private UserMapper userMapper;

    //用户登录
    public User userLogin(String username, String password){
        List<User> list = userMapper.userLogin(username,password);
        if(list.size() == 0)
            return null;
        return list.get(0);
    }

    /**
     * 注册
     * 增加用户
     */
    public void register(User user) {
        userMapper.addUser(user);
    }
    /**
     * 根据用户名查询
     */
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

}

