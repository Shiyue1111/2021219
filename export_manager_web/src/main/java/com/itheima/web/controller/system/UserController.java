package com.itheima.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Dept;
import com.itheima.domain.system.User;
import com.itheima.service.system.DeptService;
import com.itheima.service.system.UserService;
import com.itheima.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.ServerSocket;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

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
    public String toUpdate(String id){
        //1.查询当前用户的信息
        User user=userService.findById(id);
        request.setAttribute("user",user);

        //2.查询所有部门的信息
        List<Dept> deptList = deptService.findAll(getCompanyId());
        request.setAttribute("deptList",deptList);

        //3.不再回显密码
        user.setPassword(null);
        return "system/user/user-update";
    }
    @RequestMapping(name = "用户的新增或修改" ,value = "/edit")
    public String edit(User user){
        //密码加密
        if (StringUtils.isEmpty(user.getPassword())){
            String pass=new Md5Hash(user.getPassword(),user.getEmail(),2).toString();
            user.setPassword(pass);
        }
        if (StringUtils.isEmpty(user.getId())){
            //1.设置用户的id
            user.setId(UUID.randomUUID().toString());
            //2.设置企业信息
            user.setCompanyId(getCompanyId());
            user.setCompanyName(getCompanyName());
            //3.调用service保存操作
            userService.save(user);
        }else {
            userService.update(user);
        }
        return "redirect:/system/user/list.do";
    }

    @RequestMapping(name = "删除用户",value = "/delete")
    public String delete(String id){
        userService.delete(id);
        return "redirect:/system/user/list.do";
    }
}
