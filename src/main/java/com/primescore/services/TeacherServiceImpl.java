package com.primescore.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.primescore.entities.Login;
import com.primescore.entities.Teacher;
import com.primescore.repository.TeacherRepository;
import com.primescore.repository.UserRepository;

public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	TeacherRepository trepository;
	
	@Autowired
	UserRepository userepository;

	@Override
	public Teacher addTeacher(Teacher teacher) {
		Teacher addteacher = trepository.save(teacher);
		return addteacher;
	}

	@Override
	public Teacher editTeacherByusername(Teacher teacher) {
		Teacher editteacher = trepository.findById(teacher.getTeacherId()).orElse(null);
		editteacher.setEmail(teacher.getEmail());
		editteacher.setName(teacher.getName());
		return trepository.save(editteacher);
	}

	@Override
	public void deleteTeacherbyid(int id) {
		trepository.deleteById(id);
	}

	
	
}
