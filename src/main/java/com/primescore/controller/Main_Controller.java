package com.primescore.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.primescore.entities.Login;
import com.primescore.entities.Parent;
import com.primescore.entities.Student;
import com.primescore.entities.Teacher;
import com.primescore.helper.Message;
import com.primescore.repository.TeacherRepository;
import com.primescore.repository.UserRepository;

@Controller
public class Main_Controller {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private BCryptPasswordEncoder passwordencoder;

	@GetMapping("/")
	public String home(Model m) {
		m.addAttribute("title", "Primescore-home");
		return "index";
	}

	@GetMapping("/choosesignup")
	public String choosesignup() {
		return "choosesignup";
	}

	@GetMapping("/signup-student")
	public String studentSignup(Model m) {
		m.addAttribute("user", new Login());
		return "ssignup";
	}

	@GetMapping("/signup-teacher")
	public String TeacherSignup(Model m) {
		m.addAttribute("user", new Login());
		return "tsignup";
	}

	@GetMapping("/signup-parent")
	public String ParentSignup(Model m) {
		m.addAttribute("user", new Login());
		return "psignup";
	}

	// student handler
	@PostMapping("/register-student")
	public String register(@Valid @ModelAttribute("user") Login user, BindingResult result,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model m,
			HttpSession session) {
		try {
			if (!agreement) {
				System.out.println("you have not checked the agreement");
				throw new Exception("you have not checked the terms and conditioin part");
			}
			if (result.hasErrors()) {
				m.addAttribute("user", user);
				return "ssignup";
			}
			/* generating the parentcode */
			String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
			String numbers = "0123456789";
			String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
			StringBuilder sb = new StringBuilder();
			Random random = new Random();
			int length = 5;

			for (int i = 0; i < length; i++) {
				int index = random.nextInt(alphaNumeric.length());
				char randomChar = alphaNumeric.charAt(index);
				sb.append(randomChar);
			}
			String randomString = sb.toString();
			
			
			
			user.setPassword(passwordencoder.encode(user.getPassword()));
			user.setRole("ROLE_STUDENT");
			Calendar calendar = Calendar.getInstance();  
			
			Student student=new Student();
			student.setEmail(user.getEmail());
			student.setName(user.getName());
			student.setIdCreated(calendar);
			student.setParentcode(randomString);
			student.setLogin(user);
			user.setStudent(student);
			
			userRepository.save(user);
			session.setAttribute("message", new Message("successfully Registered !!!", "alert-success"));
			m.addAttribute("user", new Login());
			return "ssignup";
		} catch (Exception e) {
			session.setAttribute("message", new Message("somethimg went wrong " + e.getMessage(), "alert-danger"));
			return "ssignup";
		}
	}

	// teacher handler
	@PostMapping("/register-teacher")
	public String registerteacher(@Valid @ModelAttribute("user") Login user, BindingResult result,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model m,
			HttpSession session) {
		try {
			if (!agreement) {
				System.out.println("you have not checked the agreement");
				throw new Exception("you have not checked the terms and conditioin part");
			}
			if (result.hasErrors()) {
				m.addAttribute("user", user);
				return "tsignup";
			}
			user.setPassword(passwordencoder.encode(user.getPassword()));
			user.setRole("ROLE_TEACHER");
			Calendar calendar = Calendar.getInstance();  
			Teacher teacher=new Teacher();
			teacher.setEmail(user.getEmail());
			teacher.setName(user.getName());
			teacher.setIdCreated(calendar.getInstance());
			user.setTeacher(teacher);
			teacher.setLogin(user);
			userRepository.save(user);
			session.setAttribute("message", new Message("successfully Registered !!!", "alert-success"));
			m.addAttribute("user", new Login());
			return "tsignup";
		} catch (Exception e) {
			session.setAttribute("message", new Message("somethimg went wrong " + e.getMessage(), "alert-danger"));
			return "tsignup";
		}
	}

	// teacher handler
	@PostMapping("/register-parent")
	public String registerparent(@Valid @ModelAttribute("user") Login user, BindingResult result,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model m,
			HttpSession session) {
		try {
			if (!agreement) {
				System.out.println("you have not checked the agreement");
				throw new Exception("you have not checked the terms and conditioin part");
			}
			if (result.hasErrors()) {
				m.addAttribute("user", user);
				return "psignup";
			}
			user.setPassword(passwordencoder.encode(user.getPassword()));
			user.setRole("ROLE_PARENT");
			
			Calendar calendar = Calendar.getInstance(); 
			
			Parent parent=new Parent();
			parent.setEmail(user.getEmail());
			parent.setName(user.getName());
			parent.setIdCreated(calendar);
			parent.setLogin(user);
			user.setParent(parent);

			userRepository.save(user);
			session.setAttribute("message", new Message("successfully Registered !!!", "alert-success"));
			m.addAttribute("user", new Login());
			return "psignup";
		} catch (Exception e) {
			session.setAttribute("message", new Message("somethimg went wrong " + e.getMessage(), "alert-danger"));
			return "psignup";
		}
	}
	
	//Login for all
	@GetMapping("/loginform")
	public String loginforall() {
		return "login";
	}
	
	
	//login choose
	@GetMapping("/loginchoose")
	public String loginuser() {
		return "chooselogin";
	}
	
	@GetMapping("/faild")
	public String failed() {
		return "error";
	}

}
