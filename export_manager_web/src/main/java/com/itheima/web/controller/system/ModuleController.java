package com.itheima.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Module;

import com.itheima.service.system.ModuleService;
import com.itheima.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.NVList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/system/module")
public class ModuleController extends BaseController {

    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value = "/list", name = "模块列表查询")
    public String list(
            @RequestParam(defaultValue = "1", value = "page") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        PageInfo<Module> pageInfo = moduleService.findByPage(pageNum, pageSize);
        request.setAttribute("page", pageInfo);
        return "/system/module/module-list";
    }

    @RequestMapping(value = "/toAdd", name = "跳转到模块新增页面")
    public String toAdd() {
        //1.回显上级模块
        List<Module> menus = moduleService.findAll();
        request.setAttribute("menus", menus);
        return "/system/module/module-add";
    }

    @RequestMapping(value = "toUpdate", name = "跳转到模块修改页面")
    public String toUpdate(String id) {
        //回显模块信息
        Module module = moduleService.findById(id);
        request.setAttribute("module", module);
        List<Module> menus = moduleService.findAll();
        request.setAttribute("menus", menus);
        return "/system/module/module-update";
    }

    @RequestMapping(value = "edit", name = "模块的新增或修改")
    public String edit(Module module) {
        if (StringUtils.isEmpty(module.getId())) {
            module.setId(UUID.randomUUID().toString());
            moduleService.save(module);
        } else {
            moduleService.update(module);
        }
        return "redirect:/system/module/list.do";
    }

    @RequestMapping(name = "删除模块",value = "delete")
    public String delete(String id){
        moduleService.delete(id);
        return "redirect:/system/module/list.do";
    }
}
