package com.primescore.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String answerContent;
	private boolean correct;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private Question question;
	@ManyToOne
	private Quiz quiz_ans;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
	
	
	public Quiz getQuiz_ans() {
		return quiz_ans;
	}
	public void setQuiz_ans(Quiz quiz_ans) {
		this.quiz_ans = quiz_ans;
	}
	
	
	public Answer(int id, String answerContent, boolean correct, Question question, Quiz quiz_ans) {
		super();
		this.id = id;
		this.answerContent = answerContent;
		this.correct = correct;
		this.question = question;
		this.quiz_ans = quiz_ans;
	}
	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
