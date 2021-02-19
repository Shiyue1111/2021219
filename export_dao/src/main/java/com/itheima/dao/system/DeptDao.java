package com.itheima.dao.system;

import com.itheima.domain.system.Dept;

import java.util.List;

public interface DeptDao {
    List<Dept> findAll(String companyId);

    void save(Dept dept);

    Dept findById(String companyId);

    void update(Dept dept);

    void delete(String id);
}
