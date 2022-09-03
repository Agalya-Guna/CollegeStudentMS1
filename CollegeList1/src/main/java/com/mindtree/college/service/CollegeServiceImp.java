package com.mindtree.college.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mindtree.college.entity.College;
import com.mindtree.college.repository.CollegeRepository;
import com.mindtree.college.vo.CollegeVO;
import com.mindtree.college.vo.Student;

@Component
@Service
public class CollegeServiceImp implements CollegeService {

	@Autowired
	CollegeRepository colRepo;

	@Autowired
	RestTemplate restTemp;
	
	@Override
	public College add(College college) {
		int id = colRepo.findAll().size() + 1;
		college.setId(id);
		colRepo.save(college);
		return college;
	}

	@Override
	public List<College> addList(List<College> college) {
		colRepo.saveAll(college);
		return college;
	}

	@Override
	public List<College> getAll() {
		return colRepo.findAll();
	}

	@Override
	public List<CollegeVO> StudentListByAscName() {
		List<CollegeVO> fullList = this.getAll().stream().map(p -> {
			College colg = p;
		//	System.out.println(colg.getId());
			List<Student> slist = Arrays.asList(restTemp
					.getForEntity("http://STUDENT-SERVICE/student/cid/" + colg.getId(), Student[].class).getBody());
			return new CollegeVO(colg, slist);
		}).collect(Collectors.toList());
		return fullList;
	}

	@Override
	public List<CollegeVO> StudentListByDescAge(String stream) {
		List<CollegeVO> fulllist = this.getAll().stream().map(p -> {
			College colg = p; 
			List<Student> slist = Arrays.asList(
					restTemp.getForEntity("http://STUDENT-SERVICE/student/"+ colg.getId() + stream, Student[].class).getBody());
			return new CollegeVO(colg, slist);
		}).collect(Collectors.toList());
		return fulllist;
	}

	@Override
	public College updateall(College college) {
		// TODO Auto-generated method stub
		return colRepo.save(college);
	}

	@Override
	public void deleteCollege(int id) {
		colRepo.deleteById(id);
	}

}
