package com.mindtree.college.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mindtree.college.entity.College;
import com.mindtree.college.service.CollegeServiceImp;
import com.mindtree.college.vo.CollegeVO;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
@RequestMapping("/college")
public class CollegeController {

	@Autowired
	private CollegeServiceImp colService;
	
	@Autowired
	RestTemplate restTemp;
	private static final String COLLEGE="college";
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody College college) {
		try {
		College col= colService.add(college);
		return new ResponseEntity<College>(col,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/addlist")
	public ResponseEntity<?> addList(@RequestBody List<College> college){
		try {
			 List<College> col=colService.addList(college);
			 return new ResponseEntity<List<College>>(col,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	@GetMapping("/")
	public ResponseEntity<?> getAll(){
		try{
			List<College> col= colService.getAll();
			 return new ResponseEntity<List<College>>(col,HttpStatus.FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/ascname")
	@CircuitBreaker(fallbackMethod = "handleStudentDownTime", name = COLLEGE)
	 public ResponseEntity<List<CollegeVO>> StudentListByAscName(){
		 try{
			 List<CollegeVO> col=colService.StudentListByAscName();
		 return new ResponseEntity<List<CollegeVO>>(col,HttpStatus.OK);
    	}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
	 }
	 public ResponseEntity<List<CollegeVO>> handleStudentDownTime(Exception e){
	     List<CollegeVO> col=colService.handleStudentDownTime(e);
		 return new ResponseEntity<List<CollegeVO>>(col,HttpStatus.OK);
	 }

	@GetMapping("/descage/{stream}")
	@CircuitBreaker(fallbackMethod = "handleStudentDownTime", name = "college")
	public ResponseEntity<?> StudentListByDescAge(@PathVariable(value="stream") String stream){
		try{
			 List<CollegeVO> col=colService.StudentListByDescAge(stream);
		 return new ResponseEntity<List<CollegeVO>>(col,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
	}
	 
	@PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody College college){
    	try {
    		College col=colService.updateall(college);
			return new ResponseEntity<College>(col, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} 
    }
	 @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") int id){
	    	try {
	    		colService.deleteCollege(id);
	    		return new ResponseEntity<College>(HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	    }
	
}
