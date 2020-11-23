package com.primescore.entities;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "quiz_name")
	private String name;
	private String Description;
	private Date creation_date;
	
	@ManyToOne
	private Teacher teacher;
	@OneToOne(mappedBy = "quiz")
	private Quiz_Schedule schedule;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "quiz_qn")
	private List<Question> questions;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "quiz_ans")
	private List<Answer> answers;
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
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Quiz_Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Quiz_Schedule schedule) {
		this.schedule = schedule;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public Quiz(int id, String name, String description, Date creation_date) {
		super();
		this.id = id;
		this.name = name;
		Description = description;
		this.creation_date = creation_date;
	}
	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
