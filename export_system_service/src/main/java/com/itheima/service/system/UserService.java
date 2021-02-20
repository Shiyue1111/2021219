package com.itheima.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.User;

public interface UserService {
    PageInfo<User> findByPage(String companyId,Integer pageNum, Integer pageSize);

    void save(User user);

    void update(User user);

    User findById(String id);

    void delete(String id);
}
