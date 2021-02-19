package com.itheima.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Dept;
import com.itheima.domain.system.User;
import com.itheima.service.system.DeptService;
import com.itheima.service.system.UserService;
import com.itheima.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;
    //做一个假记录
    public String getCompanyId() {
        return "1";
    }

    public String getCompanyName() {
        return "大脸猫皮具外贸有限公司";
    }

    @RequestMapping(name = "用户列表分页", value = "/list")
    public String list(
            @RequestParam(defaultValue = "1", value = "page") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize
    ) {
        PageInfo<User> pageInfo = userService.findByPage(getCompanyId(), pageNum, pageSize);
        request.setAttribute("page", pageInfo);
        return "/system/user/user-list";
    }

    @RequestMapping(name = "跳转到用户新增页面" ,value = "/toAdd")
    public String toAdd(){
        //回显部门信息
        List<Dept> deptList =deptService.findAll(getCompanyId());
        request.setAttribute("deptList",deptList);
        return "system/user/user-add";
    }


    @RequestMapping(name = "跳转到用户修改页面" ,value = "/toUpdate")
    public String toUpdate(){
        return "system/user/user-update";
    }
}
