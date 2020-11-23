package com.primescore.entities;

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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name ="class_info")
public class Classes {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "class_id")
	private int id;
	@Column(name = "class_name",nullable = false,length = 20)
	@NotBlank(message = "classname should not be blank")
	@Length(max = 20,message = "classname should be between 20 characters")
	private String name;
	@Column(nullable = false,length = 10)
	@NotBlank(message = "standrad should not be blank")
	@Length(max = 20,message = "standrad should be between 20 characters")
	private String standrad;
	@Column(name = "class_description",nullable = true,length = 100)
	@NotBlank(message = "please provide some description of your class")
	@Length(max = 100,message = "description should be between 100 characters")
	private String descrip;
	@Column(nullable = false,length = 10,unique = true)
	private String classcode;
	private boolean status;
	
	/* mapping */
	@ManyToOne(cascade = CascadeType.ALL)
	private Teacher teacher;
	
	@ManyToMany
	private List<Student> student;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "classassignment")
	private List<Assignment> assignments;
	
	
	@OneToMany(mappedBy = "classes")
	private List<Quiz_Schedule> quiz_schedule;
	
	
	
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
	public String getStandrad() {
		return standrad;
	}
	public void setStandrad(String standrad) {
		this.standrad = standrad;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public String getClasscode() {
		return classcode;
	}
	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	
	public List<Student> getStudent() {
		return student;
	}
	public void setStudent(List<Student> student) {
		this.student = student;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public List<Assignment> getAssignments() {
		return assignments;
	}
	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
	
	public List<Quiz_Schedule> getQuiz_schedule() {
		return quiz_schedule;
	}
	public void setQuiz_schedule(List<Quiz_Schedule> quiz_schedule) {
		this.quiz_schedule = quiz_schedule;
	}
	
	public Classes(int id,
			@NotBlank(message = "classname should not be blank") @Length(max = 20, message = "classname should be between 20 characters") String name,
			@NotBlank(message = "standrad should not be blank") @Length(max = 20, message = "standrad should be between 20 characters") String standrad,
			@NotBlank(message = "please provide some description of your class") @Length(max = 100, message = "description should be between 100 characters") String descrip,
			String classcode, Teacher teacher, List<Student> student) {
		super();
		this.id = id;
		this.name = name;
		this.standrad = standrad;
		this.descrip = descrip;
		this.classcode = classcode;
		this.teacher = teacher;
		this.student = student;
	}
	public Classes() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Classes [id=" + id + ", name=" + name + ", standrad=" + standrad + ", descrip=" + descrip
				+ ", classcode=" + classcode + ", teacher=" + teacher + ", student=" + student + "]";
	}
	
}

