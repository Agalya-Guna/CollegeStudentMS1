package com.mindtree.college.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindtree.college.entity.College;
import com.mindtree.college.vo.CollegeVO;


@Service
public interface CollegeService {
	
	public College add(College college);
	
	public List<College> addList(List<College> college);
	
	public List<College> getAll();
	
	public List<CollegeVO> StudentListByAscName();

	public List<CollegeVO> StudentListByDescAge(String stream);
	
	public College updateall(College college);
	
	public void deleteCollege(int id);
	
	
	
}
