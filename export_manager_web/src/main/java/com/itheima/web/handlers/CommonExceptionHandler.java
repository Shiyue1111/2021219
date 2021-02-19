package com.itheima.web.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler
    public String exceptionHandler(Exception e, HttpServletRequest request){
        //1.记录异常信息
                e.printStackTrace();
                request.setAttribute("errorMsg",e.getMessage());
        //2.返回一个异常页面
        return "error";
    }
}
