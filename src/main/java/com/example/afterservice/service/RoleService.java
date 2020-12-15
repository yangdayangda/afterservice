package com.example.afterservice.service;

import com.example.afterservice.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRole();

    void addRole(Role role);

    void deleteRole(String name);
}
