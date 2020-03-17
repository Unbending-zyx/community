package com.student.community.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    //图片上传的真实路径
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    //图片读取的请求路径
    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

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
        sessionRegistry.excludePathPatterns("/static/image/**");
//        sessionRegistry.excludePathPatterns("/images/**");
//        sessionRegistry.excludePathPatterns("/fonts/**");
//        sessionRegistry.excludePathPatterns("/css/**");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticAccessPath+"**")
                .addResourceLocations("file:"+uploadFolder,"classpath:/static/image/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

}