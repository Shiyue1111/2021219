package com.itheima.dao.system;

import com.itheima.domain.system.Role;

import java.util.List;

public interface RoleDao {
    
    List<Role> findAll(String companyId);

    void save(Role role);

    Role findById(String id);

    void update(Role role);

    void delete(String id);
}