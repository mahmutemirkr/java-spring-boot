package com.jvtpe.controller;

import com.jvtpe.domain.Student;
import com.jvtpe.dto.StudentDTO;
import com.jvtpe.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentConttoller {

    Logger logger = LoggerFactory.getLogger(StudentConttoller.class);

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


    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentWithPath(@PathVariable("id") Long id){

        Student student = studentService.findStudent(id);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteStudent(@PathVariable("id") Long id){

        studentService.deleteStudent(id);

        Map<String , String> map = new HashMap<>();
        map.put("message","Student is deleted successfuly");
        map.put("status","true");

        return ResponseEntity.ok(map);

    }


    @PutMapping("{id}") // http://localhost:8080/students/1  + PUT + JSON
    public ResponseEntity<Map<String,String>> updateStudent(@PathVariable("id") Long id, @Valid
    @RequestBody StudentDTO studentDTO) {
        studentService.updateStudent(id,studentDTO);

        Map<String,String> map = new HashMap<>();
        map.put("message","Student is updated successfuly");
        map.put("status" ,"true");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Student>> getAllWithPage(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String prop,
            @RequestParam("direction") Sort.Direction direction) {


        Pageable pageable = PageRequest.of(page,size,Sort.by(direction,prop));
        Page<Student> studentPage = studentService.getAllWithPage(pageable);

        return ResponseEntity.ok(studentPage);

    }

    @GetMapping("/querylastname")
    public ResponseEntity<List<Student>> getStudentByLastName(@RequestParam("lastName") String lastName){

        List<Student> list = studentService.findStudent(lastName);
        return ResponseEntity.ok(list);

    }

    @GetMapping("/grade/{grade}")
    public ResponseEntity<List<Student>> getStudentsEqualsGrade(@PathVariable("grade") Integer grade) {

        List<Student> list = studentService.findAllEqualsGrade(grade);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/query/dto")   //  http://localhost:8080/students/query/dto?id=1
    public ResponseEntity<StudentDTO> getStudentDTO(@RequestParam("id") Long id) {

        StudentDTO studentDTO = studentService.findStudentDTOById(id);
        return ResponseEntity.ok(studentDTO);

    }

    @GetMapping("/welcome")
    public String welcome(HttpServletRequest httpServletRequest){

        logger.warn("-------------------- Welcome {}", httpServletRequest.getServletPath());
        return "Student Controller";

    }

}
