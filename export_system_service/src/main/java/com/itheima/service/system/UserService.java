package com.itheima.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.User;

public interface UserService {
    PageInfo<User> findByPage(String companyId,Integer pageNum, Integer pageSize);
}
