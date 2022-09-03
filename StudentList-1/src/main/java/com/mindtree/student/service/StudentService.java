package com.mindtree.student.service;

import java.util.List;

import com.mindtree.student.entity.Student;

public interface StudentService {

    public List<Student> getAllStudents();
	
	public Student getStudent(Integer id); 
	
	public List<Student> getStudents(int collegeId);
	
	public List<Student> getStudentByAscName(int collegeId);
	
	public List<Student> getStudentsList(int cid,String stream);
	
	public List<Student> getStudentByAgeDesc(String stream);
	
	public Student add(Student student);
	
	public List<Student> addStudentsList(List<Student> studentList);
	
	public  Student updateStudentName(Integer id,String student);
	
	public Student updateStudent(Student student);

	public Student updateStudentStream(Integer id,String student);
	
	public String deleteStudent(Integer id);

	
}
