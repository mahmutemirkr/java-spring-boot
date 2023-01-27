package com.jvtpe.service;

import com.jvtpe.domain.Role;
import com.jvtpe.domain.User;
import com.jvtpe.domain.enums.UserRole;
import com.jvtpe.dto.UserRequest;
import com.jvtpe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;


    public void saveUser(UserRequest userRequest) {

        User myUser = new User();
        myUser.setFirstName(userRequest.getFirstName());
        myUser.setLastName(userRequest.getLastName());
        myUser.setUserName(userRequest.getUserName());
        String password = userRequest.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        myUser.setPassword(encodedPassword);

        Role role = roleService.getRoleByType(UserRole.ROLE_ADMIN);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        myUser.setRoles(roles);

        userRepository.save(myUser);


    }
}
