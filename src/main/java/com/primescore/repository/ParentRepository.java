package com.primescore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.primescore.entities.Parent;

public interface ParentRepository extends JpaRepository<Parent, Integer> {
	@Query("select p from Parent p where p.email= :email")
	public Parent getParentByEmail(@Param("email")String email);
	
}
