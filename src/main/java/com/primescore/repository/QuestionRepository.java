package com.primescore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.primescore.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
 
	public Question findById(int id);
}
