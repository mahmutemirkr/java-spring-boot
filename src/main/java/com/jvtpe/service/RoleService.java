package com.jvtpe.service;

import com.jvtpe.domain.Role;
import com.jvtpe.domain.enums.UserRole;
import com.jvtpe.exception.ResourceNotFoundException;
import com.jvtpe.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleByType(UserRole roleType) {

        Role role = roleRepository.findByName(roleType).orElseThrow(
                ()-> new ResourceNotFoundException("Role NotFound")
        );
        return role;
    }
}
