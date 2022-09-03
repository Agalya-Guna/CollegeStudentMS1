package com.mindtree.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.student.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
