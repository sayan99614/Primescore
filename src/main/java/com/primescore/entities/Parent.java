package com.primescore.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Parent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "parent_id")
	private int id;
	@Column(name = "parent_name",nullable = false,length = 45)
	private String name;
	@Column(name = "parent_email",nullable = false,length = 55,unique = true)
	private String email;
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Calendar idCreated;
	
	/*join column*/
	
	@OneToOne(mappedBy = "parent")
	private Login login;
	
	@OneToOne
	private Student student;
	
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
	
	
	public Calendar getIdCreated() {
		return idCreated;
	}
	public void setIdCreated(Calendar idCreated) {
		this.idCreated = idCreated;
	}
	
	
	
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public Parent(int id, String name, String email, Calendar idCreated) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.idCreated = idCreated;
	}
	public Parent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
