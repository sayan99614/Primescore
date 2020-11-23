package com.primescore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.primescore.entities.Classes;

public interface ClassesRepository extends JpaRepository<Classes, Integer> {
	
	@Query("select c from Classes c where c.classcode= :classcode")
	public Classes getClassByClasscode(@Param("classcode") String classcode);
	
	public Classes findById(int id);
	
	@Query("select c from Classes c where c.name= :name")
	public Classes getClassByClassName(@Param("name") String name);
}
