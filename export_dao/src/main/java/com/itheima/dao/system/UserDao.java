package com.itheima.dao.system;
import com.itheima.domain.system.User;

import java.util.List;

public interface UserDao {
    List<User> findAll(String companyId);

    void save(User user);

    void update(User user);

    User findById(String id);

    void delete(String id);
}
