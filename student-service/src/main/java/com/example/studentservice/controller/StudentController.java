package com.example.studentservice.controller;

import com.example.studentservice.request.CreateStudentRequest;
import com.example.studentservice.response.StudentResponse;
import com.example.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("create-student")
    public StudentResponse createStudent(@RequestBody CreateStudentRequest studentRequest) {
        System.out.println("student Request : " + studentRequest);
        return studentService.createStudent(studentRequest);
    }

    @GetMapping("getStudentById/{studentId}")
    public StudentResponse getStudentById(@PathVariable long studentId) {
        return studentService.getById(studentId);
    }


}
