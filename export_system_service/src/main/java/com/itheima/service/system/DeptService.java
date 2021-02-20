package com.itheima.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Dept;

import java.util.List;

public interface DeptService {
    PageInfo<Dept> findByPage(String companyId, Integer pageNum, Integer pageSize);

    void save(Dept dept);

    List<Dept> findAll(String companyId);

    Dept findById(String companyId);

    void update(Dept dept);

    void delete(String id);

    String findDeptParentId(String id);
}
