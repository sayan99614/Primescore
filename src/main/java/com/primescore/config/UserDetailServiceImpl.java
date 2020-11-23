package com.primescore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.primescore.entities.Login;
import com.primescore.repository.UserRepository;

public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login user = userRepository.getUserByUsername(username);
		if(user== null) {
			throw new UsernameNotFoundException("the user does not exist");
		}
		Customuserdetails userdetails=new Customuserdetails(user);
		return userdetails;
	}

}
