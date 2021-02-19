package com.itheima.web.controller.company;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Company.Company;
import com.itheima.service.company.CompanyService;
import com.itheima.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/list", name = "企业列表分页查询")
    public String findByPage(
            @RequestParam(defaultValue = "1", value = "page") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        PageInfo<Company> pageInfo = companyService.findByPage(pageNum, pageSize);
        request.setAttribute("page", pageInfo);
        return "/company/company-list";
    }

    @RequestMapping(name = "跳转企业新增页面", value = "/toAdd")
    public String toAdd() {
        return "/company/company-add";
    }

    @RequestMapping(name = "企业新增或修改", value = "/edit")
    public String edit(Company company) {
        if (StringUtils.isEmpty(company.getId())){
            company.setId(UUID.randomUUID().toString());
            companyService.save(company);
        }else {
            companyService.update(company);
        }

        return "redirect:/company/list.do";
    }

    @RequestMapping(name = "跳转到企业修改页面", value = "/toUpdate")
    public String toUpdate(String id) {
        Company company = companyService.findById(id);
        request.setAttribute("company", company);
        return "/company/company-update";
    }
    @RequestMapping(name = "删除企业",value = "/delete")
    public  String delete(String id){
        companyService.delete(id);
        return "redirect:/company/list.do";    }

}
