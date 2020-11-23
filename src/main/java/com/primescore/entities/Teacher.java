package com.primescore.entities;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int teacherId;
	@Column(name = "teacher_name", nullable = false, length = 45)
	private String name;
	@Column(nullable = false,length = 55)
	private String email;
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Calendar idCreated;

	/* mapping */
	@OneToOne(mappedBy = "teacher")
	private Login login;
	
	@OneToMany(mappedBy = "teacher")
	private List<Classes> classes;
	
	@OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
	private List<Quiz> quiz ;
	
	
	
	public List<Quiz> getQuiz() {
		return quiz;
	}

	public void setQuiz(List<Quiz> quiz) {
		this.quiz = quiz;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getIdCreated() {
		return idCreated;
	}

	public void setIdCreated(Calendar idCreated) {
		this.idCreated = idCreated;
	}
	
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	
	public List<Classes> getClasses() {
		return classes;
	}

	public void setClasses(List<Classes> classes) {
		this.classes = classes;
	}

	public Teacher(int teacherId, String name, String email, Calendar idCreated) {
		super();
		this.teacherId = teacherId;
		this.name = name;
		this.email = email;
		this.idCreated = idCreated;
	}

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

}
