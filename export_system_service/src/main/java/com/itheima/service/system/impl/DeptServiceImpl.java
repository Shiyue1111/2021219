package com.itheima.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.system.DeptDao;
import com.itheima.domain.system.Dept;
import com.itheima.service.system.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private  DeptDao  deptDao;
    @Override
    public PageInfo<Dept> findByPage(String companyId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
       List<Dept> list= deptDao.findAll(companyId);
        return  new PageInfo<>(list,5);
    }

    @Override
    public void save(Dept dept) {
        deptDao.save(dept);
    }

    @Override
    public List<Dept> findAll(String companyId) {
        return deptDao.findAll(companyId);
    }

    @Override
    public Dept findById(String companyId) {
        return deptDao.findById(companyId);
    }

    @Override
    public void update(Dept dept) {
        deptDao.update(dept);
    }

    @Override
    public void delete(String id) {
        deptDao.delete(id);
    }
}
