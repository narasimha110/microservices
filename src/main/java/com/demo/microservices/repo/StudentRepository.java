package com.demo.microservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.microservices.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	
	Student findByEmail(String email);

    Student findByEmailAndPassword(String email, String password);
}
