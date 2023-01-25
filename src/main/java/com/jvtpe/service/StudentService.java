package com.jvtpe.service;

import com.jvtpe.domain.Student;
import com.jvtpe.dto.StudentDTO;
import com.jvtpe.exception.ConflictException;
import com.jvtpe.exception.ResourceNotFoundException;
import com.jvtpe.repository.IStudentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class StudentService {

    @Autowired
    private IStudentRepository iStudentRepository;

    public List<Student> getALl(){

        return iStudentRepository.findAll();

    }

    public void createStudent(Student student){

        if(iStudentRepository.existsByEmail(student.getEmail())){
            throw new ConflictException("Email is already exist");
        }
        iStudentRepository.save(student);
    }

    public Student findStudent(Long id){

        return iStudentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Student not found with id:" + id));

    }


    public void deleteStudent(Long id) {

        Student student = findStudent(id);
        iStudentRepository.delete(student);

    }

    public void updateStudent(Long id, StudentDTO studentDTO) {


        boolean existEmail = iStudentRepository.existsByEmail(studentDTO.getEmail());
        Student student = findStudent(id);

        if( existEmail && !studentDTO.getEmail().equals(student.getEmail()) ) {
            throw new ConflictException("Email is already exist ");
        }

        student.setName(studentDTO.getName());
        student.setLastName(studentDTO.getLastName());
        student.setGrade(studentDTO.getGrade());
        student.setEmail(studentDTO.getEmail());
        student.setPhoneNumber(studentDTO.getPhoneNumber());

        iStudentRepository.save(student);
    }

    public Page<Student> getAllWithPage(Pageable pageable) {

        return iStudentRepository.findAll(pageable);
    }

    public List<Student> findStudent(String lastName){

        return iStudentRepository.findByLastName(lastName);
    }

    public List<Student> findAllEqualsGrade(Integer grade) {

        return iStudentRepository.findAllEqualsGradeWithSQL(grade);

    }


    public StudentDTO findStudentDTOById(Long id) {

        return iStudentRepository.findStudentDTOById(id).orElseThrow(()->
                new ResourceNotFoundException("Student not found with id : " + id));
    }
}
