package com.student.community.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        SessionInterceptor sessionInterceptor = new SessionInterceptor();
        InterceptorRegistration sessionRegistry = registry.addInterceptor(sessionInterceptor);
        // 拦截路径
        sessionRegistry.addPathPatterns("/**");//.excludePathPatterns("/view/toLogin","/login","/view/toReg","/loginout");
        // 排除路径
        sessionRegistry.excludePathPatterns("/login/**");
        sessionRegistry.excludePathPatterns("/");
        sessionRegistry.excludePathPatterns("/articleListQuary");

        sessionRegistry.excludePathPatterns("/static/**");
        sessionRegistry.excludePathPatterns("/js/**");
        sessionRegistry.excludePathPatterns("/image/**");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}