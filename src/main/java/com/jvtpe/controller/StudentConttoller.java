package com.jvtpe.controller;

import com.jvtpe.domain.Student;
import com.jvtpe.service.StudentService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentConttoller {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAll(){

        List<Student> students = studentService.getALl();
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> createStudent(@Valid @RequestBody Student student){

        studentService.createStudent(student);

        Map<String,String> map = new HashMap<>();
        map.put("message","Student is created successfully");
        map.put("status","true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);


    }


    @GetMapping("/query")
    public ResponseEntity<Student> getStudent(@RequestParam("id") Long id){

       Student student = studentService.findStudent(id);

       return ResponseEntity.ok(student);

    }


}
