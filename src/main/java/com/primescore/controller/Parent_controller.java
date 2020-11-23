package com.primescore.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.primescore.entities.Parent;
import com.primescore.entities.Student;
import com.primescore.repository.ParentRepository;
import com.primescore.repository.StudentRepository;

@Controller
@RequestMapping("/parent")
public class Parent_controller {
	@Autowired
	ParentRepository parentrepository;
	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping("/")
	public String index(Model m,Principal principal) {
		m.addAttribute("title", "primescore-ParentDashbord");
		String email = principal.getName();
		Parent parent = parentrepository.getParentByEmail(email);
		m.addAttribute("parent", parent);
		return "parent/parentdash";
	}
	@PostMapping("/linkwithchild")
	public String linkwithstudent(@RequestParam("code") String parentcode,Principal principal) {
		String email = principal.getName();
		Parent parent = parentrepository.getParentByEmail(email);
		Student student = studentRepository.getStudentByStudcode(parentcode);
		
		student.setParent(parent);
		parent.setStudent(student);
		
		parentrepository.save(parent);
		
		return "redirect:/parent/";
	}
}
