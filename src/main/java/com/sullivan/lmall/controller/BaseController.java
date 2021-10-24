package com.sullivan.lmall.controller;

import com.sullivan.lmall.service.ex.*;
import com.sullivan.lmall.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 描述 控制器类的基类
 *
 * @author : 小蚊子
 * @date : 2021-10-17 00:47
 **/
public class BaseController {
    /** 操作成功的状态码 */
    public static final int OK = 200;

    // 请求处理方法，这个方法的返回值就是需要传递给前端的数据
    // 自动将异常对象传递给此方法的参数列表上
    // 当项目中产生了异常，被统一拦截到此方法中，这个方法此时就充当的是请求处理方法，方法的返回值直接给到前端
    @ExceptionHandler(ServiceException.class) // 用于统一处理跑出的异常
    public JsonResult handleException(Throwable e) {
        JsonResult result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicateException) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
            result.setMessage("用户数据不存在的异常");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
            result.setMessage("用户名的密码错误的异常");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        } else if (e instanceof UpdateException) {
            result.setState(5001);
            result.setMessage("更新数据时产生未知的异常");
        }
        return result;
    }

    /**
     * 获取session对象的uid
     *
     * @param session session对象
     * @return 当前对象的uid值
     */
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登录用户的username
     *
     * @param session session对象
     * @return 当前用户的用户名
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }

}
