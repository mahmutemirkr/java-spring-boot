package com.jvtpe.repository;

import com.jvtpe.domain.Role;
import com.jvtpe.domain.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {


    Optional<Role> findByName(UserRole type);


}
