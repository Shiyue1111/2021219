package com.itheima.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Role;
import com.itheima.service.system.RoleService;
import com.itheima.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/list", name = "查看角色列表")
    public String list(
            @RequestParam(defaultValue = "1", value = "page") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        PageInfo<Role> pageInfo = roleService.findByPage(getCompanyId(), pageNum, pageSize);
        request.setAttribute("page", pageInfo);
        return "/system/role/role-list";
    }

    @RequestMapping(name = "跳转到角色新增页面", value = "/toAdd")
    public String toAdd() {
        return "system/role/role-add";
    }

    @RequestMapping(name = "跳转到角色修改页面", value = "/toUpdate")
    public String toUpdate(String id) {
        //回显角色的信息
        Role role = roleService.findById(id);
        request.setAttribute("role", role);
        return "system/role/role-add";
    }

    @RequestMapping(name = "角色的新增或修改", value = "/edit")
    public String edit(Role role) {
        if (StringUtils.isEmpty(role.getId())) {
            role.setId(UUID.randomUUID().toString());
            role.setCompanyId(getCompanyId());
            role.setCompanyName(getCompanyName());
            roleService.save(role);
        } else {
            roleService.update(role);
        }
        return "redirect:/system/role/list.do";
    }

    @RequestMapping(value = "/delete",name = "删除角色信息")
    public String delete(String id){
        roleService.delete(id);
        return "redirect:/system/role/list.do";
    }

}
