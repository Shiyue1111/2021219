package com.itheima.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.system.UserDao;
import com.itheima.domain.system.User;
import com.itheima.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public PageInfo<User> findByPage(String companyId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> list =userDao.findAll(companyId);
        return new PageInfo<>(list,5);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }
}
