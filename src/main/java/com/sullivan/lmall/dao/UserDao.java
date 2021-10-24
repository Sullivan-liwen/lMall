package com.sullivan.lmall.dao;

import com.sullivan.lmall.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述
 *
 * @author : 小蚊子
 * @date : 2021-09-20 21:09
 **/
@Mapper
@Repository
public interface UserDao {
    /**
     * 注册成功 插入一条用户信息
     *
     * @param user 用户的数据
     * @return 受影响的行数
     */
    Integer registerUser(User user);

    /**
     * 查找是否用户名和密码正确
     *
     * @param username 用户名
     * @return user
     */
    User getUserByName(@Param("username") String username);

}
