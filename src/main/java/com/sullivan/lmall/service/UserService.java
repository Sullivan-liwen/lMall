package com.sullivan.lmall.service;

import com.sullivan.lmall.model.User;

/**
 * 描述 用户管理的service接口
 *
 * @author : 小蚊子
 * @date : 2021-09-20 21:22
 **/
public interface UserService {
    /**
     * 用户注册的方法
     * @param user 用户实例对象
     */
    void registerUser(User user);

    /**
     * 用户登录的方法
     */
    User loginUser(String username, String password);

    /**
     * 用户修改密码的方法
     */
    void changePassword(Integer uid, String username, String oldPassword, String newPassword);

    /**
     * 获得当前用户的登录信息
     */
    User getUserInfoByUid(Integer uid);

    /**
     * 修改用户的资料信息
     */
    void updateUserInfo(Integer uid, String username, User user);

    /**
     * 修改用户的头像信息
     */
    void updateAvatarByUid(Integer uid, String avatar, String username);
}
