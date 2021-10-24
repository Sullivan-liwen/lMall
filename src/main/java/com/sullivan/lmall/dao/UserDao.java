package com.sullivan.lmall.dao;

import com.sullivan.lmall.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    /**
     * 根据用户的Uid来修改用户密码
     *
     * @param uid 用户的id
     * @param password 用户新输入的密码
     * @param modifiedUser 修改的执行者
     * @param modifiedTime 修改的时间
     * @return 受影响的行数
     */
    Integer updatePasswordByUid(@Param("uid") Integer uid,
                                @Param("password") String password,
                                @Param("modifiedUser") String modifiedUser,
                                @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据用户的id查询用户的数据
     *
     * @param uid 用户的id
     * @return 如果找到则返回对象，反之返回null值
     */
    User findByUid(Integer uid);

    /**
     * 更新用户的信息
     *
     * @param user 用户的数据
     * @return 受影响的行数
     */
    Integer updateInfoByUid(User user);

}
