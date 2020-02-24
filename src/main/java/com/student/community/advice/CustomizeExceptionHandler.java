package com.student.community.advice;

import com.student.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用异常处理类
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e,ModelAndView modelAndView){
//        HttpStatus status=getStatus(request);
        if (e instanceof CustomizeException){
            modelAndView.addObject("message",e.getMessage());
        }else{
            modelAndView.addObject("message","! 发生错误 !");
        }
        modelAndView.setViewName("error");
        return modelAndView;
    }

//    private HttpStatus getStatus(HttpServletRequest request){
//        Integer statusCode=(Integer) request.getAttribute("javax.servlet.error.status_code");
//        if (statusCode==null){
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return HttpStatus.valueOf(statusCode);
//    }

}
