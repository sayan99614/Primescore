package com.primescore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.primescore.entities.Login;

public interface UserRepository extends JpaRepository<Login, Integer> {
	@Query("select u from Login u where u.email= :email")
	public Login getUserByUsername(@Param("email") String email);
	
}
