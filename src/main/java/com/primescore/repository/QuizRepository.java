package com.primescore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.primescore.entities.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
	
	public Quiz findById(int id);
}
