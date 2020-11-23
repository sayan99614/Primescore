package com.primescore.services;

import com.primescore.entities.Teacher;

public interface TeacherService {
 
	public Teacher addTeacher(Teacher teacher);
	public Teacher editTeacherByusername(Teacher teacher);
	public void deleteTeacherbyid(int id);
}
