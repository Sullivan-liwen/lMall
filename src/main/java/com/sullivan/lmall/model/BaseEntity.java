package com.sullivan.lmall.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述 实体类的基类
 *
 * @author : 小蚊子
 * @date : 2021-10-16 13:16
 */
@Setter
@Getter
public class BaseEntity implements Serializable {
  private String createdUser;
  private Date createdTime;
  private String modifiedUser;
  private Date modifiedTime;
}
