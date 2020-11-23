package com.primescore.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Quiz_Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Timestamp StartTime;
	private Timestamp EndTime;
	@OneToOne
	private Quiz quiz;
	@ManyToOne
	private Classes classes;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getStartTime() {
		return StartTime;
	}
	public void setStartTime(Timestamp startTime) {
		StartTime = startTime;
	}
	public Timestamp getEndTime() {
		return EndTime;
	}
	public void setEndTime(Timestamp endTime) {
		EndTime = endTime;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	public Quiz_Schedule(int id, Timestamp startTime, Timestamp endTime, Quiz quiz) {
		super();
		this.id = id;
		StartTime = startTime;
		EndTime = endTime;
		this.quiz = quiz;
	}
	public Quiz_Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
