package com.demo.microservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.microservices.model.Student;
import com.demo.microservices.repo.StudentRepository;

@Service
public class StudentService {

	
	 @Autowired
	    private StudentRepository studentRepository;

	    public Student addStudent(Student student) {
	        return studentRepository.save(student);
	    }

	    public List<Student> getAllStudents() {
	        return studentRepository.findAll();
	    }

	    public Student getStudentById(Long id) {
	        return studentRepository.findById(id).orElse(null);
	    }

	    public Student updateStudent(Long id, Student updatedStudent) {
	        Student existingStudent = studentRepository.findById(id).orElse(null);
	        if (existingStudent != null) {
	            existingStudent.setName(updatedStudent.getName());
	            existingStudent.setEmail(updatedStudent.getEmail());
	            existingStudent.setPassword(updatedStudent.getPassword());
	            existingStudent.setPhoneNumber(updatedStudent.getPhoneNumber());
	            return studentRepository.save(existingStudent);
	        }
	        return null;
	    }

	    public void deleteStudent(Long id) {
	        studentRepository.deleteById(id);
	    }

	    public Student login(String email, String password) {
	        return studentRepository.findByEmailAndPassword(email, password);
	    }
}
