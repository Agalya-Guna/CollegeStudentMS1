package com.mindtree.student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mindtree.student.entity.Student;
import com.mindtree.student.repository.StudentRepository;

@Service
public class StudentServiceImp implements StudentService {

	@Autowired
	StudentRepository studentRepo;

	@Override
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

	@Override
	public Student getStudent(Integer id) {
		return studentRepo.findById(id).get();
	}

	@Override
	public List<Student> getStudents(int collegeId) {
		List<Student> student = studentRepo.findAll();
		for (Student s : student) {
			if (s.getCollegeId() == collegeId) {
				return (List<Student>) s;
			}
		}
		return null;
	}

	@Override
	public Student add(Student student) {
		int id = studentRepo.findAll().size() + 1;
		student.setId(id);
		studentRepo.save(student);
		return student;
	}

	@Override
	public List<Student> addStudentsList(List<Student> studentList) {
		return studentRepo.saveAll(studentList);
	}

	@Override
	public Student updateStudentStream(Integer id, String stream) {
		Student s = studentRepo.findById(id).get();

		if (s != null) {
			s.setS_stream(stream);
			return studentRepo.save(s);
		}
		return null;
//		else
//			throw new DoctorNotFoundException("No Doctor found with id : "+id);
	}

	@Override
	public Student updateStudentName(Integer id, String student) {
		Student s = studentRepo.findById(id).get();

		if (s != null) {
			s.setName(student);
			return studentRepo.save(s);

		}
		return null;
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepo.save(student);
	}

	@Override
	public String deleteStudent(Integer id) {
		studentRepo.deleteById(id);
		return "deleted" + id;
	}

	// asc name
	@Override
	public List<Student> getStudentByAscName(int collegeId) {
		List<Student> student = studentRepo.findAll(Sort.by(Direction.ASC, "name"));
		List<Student> slist = new ArrayList<>();
		for (Student s : student) {
			if (s.getCollegeId() == collegeId)
				slist.add(s);
		}
		return slist;
	}

	// desc age
	@Override
	public List<Student> getStudentByAgeDesc(String stream) {
		List<Student> student = studentRepo.findAll(Sort.by(Direction.DESC, "age"));
		List<Student> slist = new ArrayList<>();
		for (Student s : student) {
			if (s.getS_stream().equalsIgnoreCase(stream))
				slist.add(s);
		}
		return slist;
	}

	@Override
	public List<Student> getStudentsList(int cid, String stream) {
		List<Student> stud = studentRepo.findAll(Sort.by(Direction.DESC, "age"));
		List<Student> slist = new ArrayList<>();
		for (Student s : stud) {
			if (s.getCollegeId() == cid) {
				if (s.getS_stream().equalsIgnoreCase(stream)) {
					slist.add(s);
				}
			}
		}
		return slist;
	}
}
