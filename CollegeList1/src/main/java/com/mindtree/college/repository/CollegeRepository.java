package com.mindtree.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.college.entity.College;

@Repository
public interface CollegeRepository  extends JpaRepository<College, Integer>{

}
