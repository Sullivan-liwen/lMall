package com.sullivan.lmall.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述 用户管理登录的拦截器
 *
 * @author : 小蚊子
 * @date : 2021-10-17 10:53
 */
public class LoginInterceptor implements HandlerInterceptor {
      /**
       * 检测全局session对象中是否有uid数据，如果有则放行，如果没有重定向到登录页面
       *
       * @param request 请求对象
       * @param response 响应对象
       * @param handler 处理器
       * @return true 放行 false 拦截
       * @throws Exception Exception
       */
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
          throws Exception {
          Object uid = request.getSession().getAttribute("uid");
          if (uid == null) {
              // 说明用户没有登录过系统
              response.sendRedirect("/web/login.html");
              return false;
          }
          return true;
      }
}
