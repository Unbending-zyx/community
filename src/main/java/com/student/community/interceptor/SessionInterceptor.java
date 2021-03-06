package com.student.community.interceptor;

import com.student.community.dao.INotificationDAO;
import com.student.community.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断请求时ajax
//        if(request.getHeader("X-Requested-With") != null &&
//                "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString())){
//            return true;
//        }
        User user=(User) request.getSession().getAttribute("user");
        if (user!=null){
            return true;
        }
        response.sendRedirect("/login/show");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
