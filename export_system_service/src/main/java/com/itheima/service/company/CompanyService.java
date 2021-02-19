package com.itheima.service.company;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Company.Company;

import java.util.List;

public interface CompanyService {


    void save(Company company);

    Company findById(String id);

    void update(Company company);

    void delete(String id);

    PageInfo<Company> findByPage(Integer pageNum, Integer pageSize);


}
