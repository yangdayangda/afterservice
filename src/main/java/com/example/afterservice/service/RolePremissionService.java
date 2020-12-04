package com.example.afterservice.service;

import com.example.afterservice.entity.Premission;

import java.util.List;
import java.util.Set;

public interface RolePremissionService {
    Set<String> getPreByRole(Set<String> roles);

    void updatePremission(List<String> premission, String role);

    Set<Premission> getAllPreByRoles(Set<String> roles);
}
