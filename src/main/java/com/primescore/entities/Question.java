package com.primescore.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String content;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "question",cascade = CascadeType.ALL)
	private List<Answer> answers;
	
	@ManyToOne
	private Quiz quiz_qn;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public Quiz getQuiz_qn() {
		return quiz_qn;
	}
	public void setQuiz_qn(Quiz quiz_qn) {
		this.quiz_qn = quiz_qn;
	}
	
	public Question(int id, String content, List<Answer> answers, Quiz quiz_qn) {
		super();
		this.id = id;
		this.content = content;
		this.answers = answers;
		this.quiz_qn = quiz_qn;
	}
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", content=" + content + ", answers=" + answers + ", quiz_qn=" + quiz_qn + "]";
	}
	
	
	
	
}
