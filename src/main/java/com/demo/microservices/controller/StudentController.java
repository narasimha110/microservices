package com.demo.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservices.model.LoginRequest;
import com.demo.microservices.model.Student;
import com.demo.microservices.services.OTPService;
import com.demo.microservices.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
    private StudentService studentService;
    
    @Autowired
    private OTPService otpService;
    
    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }
    
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
    
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        return studentService.updateStudent(id, updatedStudent);
    }
    
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
    
    @PostMapping("/login")
    public Student login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        return studentService.login(email, password);
        
    }
    
    @PostMapping("/send-otp")
    public String sendOTP(@RequestParam String email) {
        int otp = otpService.generateOTP(email);
        return "OTP sent to " + email + ": " + otp;
    }
    
    @PostMapping("/verify-otp")
    public String verifyOTP(@RequestParam String email, @RequestParam int otp) {
        if (otpService.verifyOTP(email, otp)) {
            return "OTP verified successfully";
        } else {
            return "Invalid OTP";
        }

}
}
