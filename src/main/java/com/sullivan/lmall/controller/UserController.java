package com.sullivan.lmall.controller;

import com.sullivan.lmall.model.User;
import com.sullivan.lmall.service.UserService;
import com.sullivan.lmall.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 描述 用户管理Controller
 *
 * @author : 小蚊子
 * @date : 2021-09-20 21:17
 **/
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    //@ResponseBody 表示此方法的响应结果以json的格式进行数据的响应给到前端
    public JsonResult<Void> registerUser(User user) {
        userService.registerUser(user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/login")
    public JsonResult<User> loginUser(String username, String password, HttpSession session) {
        User userData = userService.loginUser(username, password);
        session.setAttribute("uid", userData.getUid());
        session.setAttribute("username", userData.getUsername());
        return new JsonResult<>(OK, userData);
    }

    @RequestMapping("/update-password")
    public JsonResult<Void> updateUserPassword(String oldPassword,
                                               String newPassword, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }
}
