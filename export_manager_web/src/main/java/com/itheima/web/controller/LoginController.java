package com.itheima.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController{

	@RequestMapping("/login")
	public String login(String email,String password) {
		return "redirect:/home/main.do";
	}

    @RequestMapping("/home/main")
    public String main(){
        return "home/main";
    }

    @RequestMapping("/home/home")
    public String home(){
	    return "home/home";
    }

    //退出
    @RequestMapping(value = "/logout",name="用户登出")
    public String logout(){
        //SecurityUtils.getSubject().logout();   //登出
        return "forward:login.jsp";
    }
}
