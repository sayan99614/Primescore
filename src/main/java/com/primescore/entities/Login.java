package com.primescore.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Entity
public class Login {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "Name should not be blank")
	private String name;
	@Email(message = "Email should be in proper format eg:example@gmail.com")
	@NotBlank(message = "email should not be blank")
	private String email;
	private String role;
	@NotBlank(message = "Password should not be blank")
	private String password;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Teacher teacher;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Student student;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Parent parent;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	public Login(int id, @NotBlank(message = "Name should not be blank") String name,
			@Email(message = "Email should be in proper format eg:example@gmail.com") @NotBlank(message = "email should not be blank") String email,
			String role, @NotBlank(message = "Password should not be blank") String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.role = role;
		this.password = password;
	}
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
}
