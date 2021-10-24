package com.sullivan.lmall.service.impl;

import com.sullivan.lmall.dao.UserDao;
import com.sullivan.lmall.model.User;
import com.sullivan.lmall.service.UserService;
import com.sullivan.lmall.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 描述 用户管理Service实现类
 *
 * @author : 小蚊子
 * @date : 2021-09-20 21:23
 **/
@Service // @Service注解：将这个service实现类交给spring管理
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void registerUser(User user) {
        String username = user.getUsername();
        User result = userDao.getUserByName(username);
        if (result != null) {
            // 抛出异常
            throw new UsernameDuplicateException("用户名被占用");
        }
        // 密码加密处理的实现： md5算法的形式
        // 串 + password + 串 ==== md5算法进行加密
        // 盐值 + password + 盐值  ------ 盐值就是一个随机的字符串
        String oldPassword = user.getPassword();
        // 获取盐值（随机生成一个盐值）
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMD5Password(oldPassword, salt);
        user.setPassword(md5Password);
        user.setSalt(salt);
        // 补全数据：is_delete设置成0
        user.setIsDelete(0);
        // 补全数据：4个日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        // 执行注册业务功能的实现
        Integer rows = userDao.registerUser(user);
        if (rows != 1) {
            throw new InsertException("在用户注册的时候出现了未知的异常");
        }
    }

    @Override
    public User loginUser(String username, String password) {
        // 根据用户名称来查询用户的数据是否存在，如果不存在则抛出异常
        User result = userDao.getUserByName(username);
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在");
        }
        String oldPassword = result.getPassword();
        String salt = result.getSalt();
        String newPassword = getMD5Password(password, salt);
        if (!oldPassword.equals(newPassword)) {
            throw new PasswordNotMatchException("用户密码错误");
        }
        // 判断is_delete字段是否为1 表示被标记删除
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        // 传递当前对象的一些指定字段给前端
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User user = userDao.findByUid(uid);
        if (user == null || user.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在！");
        }
        // 原始密码和数据库中的密码进行比较
        String oldMd5Password = getMD5Password(oldPassword, user.getSalt());
        if (!user.getPassword().equals(oldMd5Password)) {
            throw new PasswordNotMatchException("密码错误!");
        }
        // 将新的密码设置到数据库中，将新的密码进行加密再去更新
        String newMd5Password = getMD5Password(newPassword, user.getSalt());
        Integer row = userDao.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if (row != 1) {
            throw new UpdateException("更新数据时产生的未知的异常！");
        }
    }

    /**
   * MD5密码加密处理
   *
   * @param password 密码
   * @param salt 盐值
   * @return string串
   */
  private String getMD5Password(String password, String salt) {
        // md5加密算法方法的调用(进行三次加密)
        for (int i = 0; i < 3; i++) {
          password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
