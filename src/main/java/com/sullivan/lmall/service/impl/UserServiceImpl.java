package com.sullivan.lmall.service.impl;

import com.sullivan.lmall.dao.UserMapper;
import com.sullivan.lmall.model.User;
import com.sullivan.lmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述
 *
 * @author : 小蚊子
 * @date : 2021-09-20 21:23
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryUserList() {
        List<User> users = userMapper.queryUserList();
        for (User user : users){
            System.out.println(user);
        }
        return users;
    }
}
