package com.sullivan.lmall.dao;

import com.sullivan.lmall.model.User;
import org.apache.ibatis.annotations.Mapper;
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
public interface UserMapper {

    List<User> queryUserList();

}
