package com.itheima.service.company.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.company.CompanyDao;
import com.itheima.domain.Company.Company;
import com.itheima.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;

/*  @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }*/

    @Override
    public void save(Company company) {
        companyDao.save(company);
    }

    @Override
    public Company findById(String id) {
        return companyDao.findById(id);
    }

    @Override
    public void update(Company company) {
        companyDao.update(company);
    }

    @Override
    public void delete(String id) {
        companyDao.delete(id);
    }

    @Override
    public PageInfo<Company> findByPage(Integer pageNum, Integer pageSize) {
        //1.设置pageNum和pagSize
        PageHelper.startPage(pageNum, pageSize);
        //2.执行一个findAll
        List<Company> list = companyDao.findAll();

        //3.返回对象
        return new PageInfo<>(list, 5);
    }

}
