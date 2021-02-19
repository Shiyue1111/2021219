package com.itheima.dao.system;
import com.itheima.domain.system.User;

import java.util.List;

public interface UserDao {
    List<User> findAll(String companyId);

}
