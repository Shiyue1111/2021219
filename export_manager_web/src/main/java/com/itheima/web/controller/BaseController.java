package com.itheima.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;

    //获取当前登录人所在企业id
    protected String getCompanyId(){
        return "1";//todo 做完登录后悔改这里的代码
    }
    //获取当前登录人所在企业名称
    protected  String getCompanyName(){
        return "大脸猫皮具外贸有限公司";
    }
}
