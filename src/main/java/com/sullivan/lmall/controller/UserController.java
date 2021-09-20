package com.sullivan.lmall.controller;

import com.sullivan.lmall.dao.UserMapper;
import com.sullivan.lmall.model.User;
import com.sullivan.lmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述
 *
 * @author : 小蚊子
 * @date : 2021-09-20 21:17
 **/
@RestController
public class UserController {
    @Autowired
    private UserService userMapper;

    @GetMapping("/list")
    public List<User> queryList(){
        List<User> users = userMapper.queryUserList();
        for (User user : users){
            System.out.println(user);
        }
        return users;
    }
}
