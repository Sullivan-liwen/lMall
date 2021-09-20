package com.sullivan.lmall.model;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

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
public class User {
    private BigDecimal userId;
    private String username;
    private String password;

    /**
     * 头像
     */
    private String userIcon;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 备注信息
     */
    private String userNote;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后登录时间
     */
    private Date loginTime;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    private int userStatus;
}
