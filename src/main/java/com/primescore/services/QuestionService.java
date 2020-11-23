package com.primescore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primescore.entities.Answer;
import com.primescore.entities.Question;
import com.primescore.repository.QuestionRepository;

@Service
public class QuestionService {
	
	@Autowired
	QuestionRepository questionRepository;
	
	
	public int findAnsweidCorrect(int questionid) {
		Question question = questionRepository.findById(questionid);
		for (Answer answer : question.getAnswers()) {
			if(answer.isCorrect()) {
				return answer.getId();
			}
		}
		return -1;
	}
}
