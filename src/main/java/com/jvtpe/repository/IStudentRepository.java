package com.jvtpe.repository;

import com.jvtpe.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

}
