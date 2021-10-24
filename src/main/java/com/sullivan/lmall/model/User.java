package com.sullivan.lmall.model;

import lombok.*;

/**
 * 描述
 *
 * @author : 小蚊子
 * @date : 2021-09-20 21:07
 **/
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    private Integer uid;
    private String username;
    private String password;

    /**
     * 盐值
     */
    private String salt;
    private String phone;
    private String email;

    /**
     * 性别:0-女，1-男
     */
    private Integer gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否删除：0-未删除，1-已删除
     */
    private Integer isDelete;
}
