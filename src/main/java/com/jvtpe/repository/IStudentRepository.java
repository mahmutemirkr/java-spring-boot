package com.jvtpe.repository;

import com.jvtpe.domain.Student;
import com.jvtpe.dto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

    List<Student> findByLastName(String lastName);

    @Query("SELECT s from Student s WHERE s.grade=:pGrade") //JPQL -> similer HQL
    List<Student> findAllEqualsGrade(@Param("pGrade") Integer grade);

    @Query(value="SELECT * FROM Student s WHERE s.grade=:pGrade" , nativeQuery = true) //NativeSQL
    List<Student> findAllEqualsGradeWithSQL(@Param("pGrade") Integer grade);

    @Query("SELECT new com.jvtpe.dto.StudentDTO(s) FROM Student s WHERE s.id=:id ")
    Optional<StudentDTO> findStudentDTOById(@Param("id") Long id);
}
