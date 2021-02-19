package com.itheima.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Dept;
import com.itheima.service.system.DeptService;
import com.itheima.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseController {

    @Autowired
    private DeptService deptService;

    //todo  做一个假数据  记录下companyId 和 companyName   登录功能完成之后调整
    public String getCompanyId() {
        return "1";
    }

    public String getCompanyName() {
        return "大脸猫皮具外贸有限公司";
    }


    @RequestMapping(value = "/list", name = "企业列表查询")
    public String list(
            @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        PageInfo<Dept> pageInfo = deptService.findByPage(getCompanyId(), pageNum, pageSize);
        request.setAttribute("page", pageInfo);

        return "/system/dept/dept-list";
    }

        @RequestMapping(value = "/toUpdate", name = "跳转部门修改页面")
        public String toUpdate(String id) {
            //1 查询当前部门信息
            Dept dept = deptService.findById(id);
            request.setAttribute("dept", dept);

            //2 查询所有的部门信息
            List<Dept> deptList = deptService.findAll(getCompanyId());
            request.setAttribute("deptList",deptList);

            return "/system/dept/dept-update";
        }

        @RequestMapping(value = "/toAdd", name = "跳转到部门新增页面")
    public String toAdd() {
        List<Dept> deptList = deptService.findAll(getCompanyId());
        request.setAttribute("deptList",deptList);

        return "/system/dept/dept-add";
    }

    @RequestMapping(value = "/edit",name = "部门的新增或修改")
    public String toEdit(Dept dept){
        //回显上级部门
        if (StringUtils.isEmpty(dept.getParent().getId())){
            dept.getParent().setId(null);
        }

        if (StringUtils.isEmpty(dept.getId())){
            //新增部门设置部门Id
            dept.setId(UUID.randomUUID().toString());
            //设置企业信息
            dept.setCompanyId(getCompanyId());
            dept.setCompanyName(getCompanyName());

            deptService.save(dept);
        }else {
            //修改部门信息
       deptService.update(dept);
        }

        return "redirect:/system/dept/list.do";
    }
    @RequestMapping(name ="删除部门",value = "/delete")
    public String delete(String id){
        deptService.delete(id);
        return "redirect:/system/dept/list.do";
    }
}