package com.sullivan.lmall.util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 描述 Json格式的数据进行响应
 *
 * @author : 小蚊子
 * @date : 2021-10-16 15:01
 **/
@Setter
@Getter
public class JsonResult<E> implements Serializable {
    /**
     * 状态码
     */
    private Integer state;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 数据
     */
    private E data;

    public JsonResult() {
        super();
    }

    public JsonResult(Integer state) {
        super();
        this.state = state;
    }

    public JsonResult(Throwable e) {
        super();
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        super();
        this.state = state;
        this.data = data;
    }
}
