package com.example.afterservice.service;

import java.util.Set;

public interface RolePremissionService {
    Set<String> getPreByRole(Set<String> roles);
}
