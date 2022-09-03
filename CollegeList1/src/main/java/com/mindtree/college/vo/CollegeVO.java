package com.mindtree.college.vo;

import java.util.List;

import com.mindtree.college.entity.College;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CollegeVO {
	
//	private int id;
//	private String name;
//	private int noOfStudents;
	private College college;
	private List<Student> student;

}
