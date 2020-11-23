package com.primescore.entities;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student_information")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int studId;
	
	@Column(name = "stud_name",nullable = false,length = 45)
	private String name;
	@Column(name = "stud_email",nullable = false,length = 100)
	private String email;
	@Column(name = "stud_classcode",length = 10)
	private String classcode;
	private String parentcode;
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Calendar idCreated;
	/* join column */
	@OneToOne(mappedBy = "student")
	private Login login;
	
	@ManyToMany(cascade = CascadeType.PERSIST,mappedBy = "student")
	private List<Classes> classes;
	
	@OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
	private Parent parent;
	
	public int getStudId() {
		return studId;
	}
	public void setStudId(int studId) {
		this.studId = studId;
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
	
	public String getClasscode() {
		return classcode;
	}
	public void setClasscode(String classcode) {
		this.classcode = classcode;
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
	
	
	public String getParentcode() {
		return parentcode;
	}
	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}
	public List<Classes> getClasses() {
		return classes;
	}
	public void setClasses(List<Classes> classes) {
		this.classes = classes;
	}
	
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	@Override
	public String toString() {
		return "Student [studId=" + studId + ", name=" + name + ", email=" + email + "]";
	}
	
	
	
	
	public Student(int studId, String name, String email, String classcode, Calendar idCreated, Login login,
			List<Classes> classes) {
		super();
		this.studId = studId;
		this.name = name;
		this.email = email;
		this.classcode = classcode;
		this.idCreated = idCreated;
		this.login = login;
		this.classes = classes;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}

