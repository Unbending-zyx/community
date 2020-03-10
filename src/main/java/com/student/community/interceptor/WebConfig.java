package com.student.community.interceptor;

import com.student.community.dao.INotificationDAO;
import org.springframework.beans.factory.annotation.Autowired;
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
        sessionRegistry.excludePathPatterns("/error");

        sessionRegistry.excludePathPatterns("/static/**");
//        sessionRegistry.excludePathPatterns("/js/**");
//        sessionRegistry.excludePathPatterns("/image/**");
//        sessionRegistry.excludePathPatterns("/images/**");
//        sessionRegistry.excludePathPatterns("/fonts/**");
//        sessionRegistry.excludePathPatterns("/css/**");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}