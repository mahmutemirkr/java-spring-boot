package com.jvtpe.service;

import com.jvtpe.domain.Student;
import com.jvtpe.exception.ConflictException;
import com.jvtpe.exception.ResourceNotFoundException;
import com.jvtpe.repository.IStudentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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
}
