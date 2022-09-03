package com.mindtree.student.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.student.entity.Student;
import com.mindtree.student.service.StudentServiceImp;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentServiceImp studentService;

	// return all students
	@GetMapping("/")
	public ResponseEntity<?> getAllStudents() {
		try {
			List<Student> slist = studentService.getAllStudents();
			return new ResponseEntity<List<Student>>(slist, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable(value="id")int id ){
		try {
			Student student=studentService.getStudent(id);
			return new ResponseEntity<Student>(student, HttpStatus.FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("stream/{stream}")
	public ResponseEntity<?> getStudentByStream(@PathVariable(value="stream")String stream){

		try {
			List<Student> slist = studentService.getStudentByAgeDesc(stream);
			return new ResponseEntity<List<Student>>(slist, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("cid/{cid}")
	public ResponseEntity<List<Student>> getStudentByName(@PathVariable(value="cid")int cid){

		try {
			List<Student> slist = studentService.getStudentByAscName(cid);
			return new ResponseEntity<List<Student>>(slist, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
//	@GetMapping("collegeid/{cid}")
//	public ResponseEntity<?> getStudents(@PathVariable(value="cid")int id){
//		try {
//			List<Student> student=studentService.getStudents(id);
//			return new ResponseEntity<List<Student>>(student, HttpStatus.FOUND);
//		}catch(Exception e) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Student student){
		try {
			student=studentService.add(student);
			return new ResponseEntity<Student>(student, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
    @PostMapping("/addlist")
	public ResponseEntity<?> addStudentsList(@RequestBody List<Student> student){
		try {
			List<Student> students=studentService.addStudentsList(student);
			return new ResponseEntity<List<Student>>(students, HttpStatus.FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody Student student){
    	try {
			Student students=studentService.updateStudent(student);
			return new ResponseEntity<Student>(students, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") int id){
    	try {
    		studentService.deleteStudent(id);
    		return new ResponseEntity<Student>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    
    @GetMapping("/{id}/{stream}")
    public ResponseEntity<?> getStudentsList(@PathVariable(value="id") int id,@PathVariable(value="stream")String stream){
    	try {
    		List<Student> slist=studentService.getStudentsList(id, stream);
    		return new ResponseEntity<List<Student>>(slist,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
   
}
