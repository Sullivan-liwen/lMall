package com.sullivan.lmall.config;

import com.sullivan.lmall.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 注册处理器拦截器
 *
 * @author : 小蚊子
 * @date : 2021-10-17 17:04
 **/
@Configuration  // 加载当前的拦截器并进行注册
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    // 配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建自定义的拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();

        // 白名单
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/user/reg");
        patterns.add("/user/login");
        patterns.add("/districts/**");
        patterns.add("/products/**");

        // 完成拦截器的注册
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}
