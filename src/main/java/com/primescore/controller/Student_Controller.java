package com.primescore.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.primescore.entities.Classes;
import com.primescore.entities.Quiz;
import com.primescore.entities.Student;
import com.primescore.helper.Message;
import com.primescore.repository.ClassesRepository;
import com.primescore.repository.QuestionRepository;
import com.primescore.repository.QuizRepository;
import com.primescore.repository.StudentRepository;
import com.primescore.services.QuestionService;

@Controller
@RequestMapping("/student")
public class Student_Controller {
	
	@Autowired
	ClassesRepository classRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	QuizRepository quizRepository;
	@Autowired
	QuestionService questionService;
	
	
	@GetMapping("/")
	public String StudentDashbord(Principal principle,Model m) {
		String name = principle.getName();
		Student student = studentRepository.getStudentByUsername(name);
		m.addAttribute("student", student);
		return"student/studentdash";
	}
	@GetMapping("/joinclass")
	public String joinclassform() {
		return "student/joinclass";
	}
	@PostMapping("/dojoin")
	public String joinbycode(@RequestParam("classcode") String classcode,Model model,Principal principal,HttpSession session)  {
		
		Classes classes = classRepository.getClassByClasscode(classcode);
		if(classes==null) {
			session.setAttribute("messageclass", new Message("invalid classcode","alert-danger"));
			return "redirect:joinclass";
		}
		if(classes.isStatus()) {
			session.setAttribute("messageclass", new Message("please ask your teacherto unlock the class","alert-danger"));
			return "redirect:joinclass";
		}
		String name = principal.getName();
		Student stud = studentRepository.getStudentByUsername(name);
		stud.getClasses().add(classes);
		stud.setClasscode(classcode);
		
		classes.getStudent().add(stud);
		studentRepository.saveAndFlush(stud);
		session.setAttribute("messageclass", new Message("Sucessfully Joined to the class","alert-success"));
		/* redirect:/student/ */
		return"redirect:joinclass";	
	}
	@GetMapping("/class/{classcode}")
	public String singleclass(@PathVariable("classcode") String classcode,Principal principal,Model m) {
		String name = principal.getName();
		Student student = studentRepository.getStudentByUsername(name);
		Classes classes = classRepository.getClassByClasscode(classcode);
		
		for (Student s : classes.getStudent()) {
			if(s.getStudId()==student.getStudId()) {
				m.addAttribute("title","singleclass");
				m.addAttribute("classes",classes);
				return "student/singleclass";
			}
		}
		
		return "student/singleclass";
	}
	@GetMapping("/download/{name}")
	public String downloadfile(@PathVariable String name,HttpServletResponse response) {
		int buffer_size= 1024*100;
		String filepath="C:\\Users\\DHEEMAN\\git\\primescore-onlineQuiz\\QUIZ-PRIMESCORE\\target\\classes\\static\\assignment\\"+name;
		
		File file =new File(filepath);
		OutputStream outstream=null;
		FileInputStream inputstream=null;
		
		if(file.exists()) {
			
			String mimeType="application/octet-stream";
			response.setContentType(mimeType);
			
			String headerkey="Content-Disposition";
			String headervalue=String.format("attachment; filename=\"%s\"", file.getName());
			
			System.out.println(headervalue);
 			
			response.setHeader(headerkey, headervalue);
			
			try {
				
				outstream=response.getOutputStream();
				inputstream=new FileInputStream(file);
				byte[] buffer=new byte[buffer_size];
				int bytesread=-1;
				
				//reading data frm input stream and writing to the output stream
				while((bytesread=inputstream.read(buffer))!=-1) {
					outstream.write(buffer,0,bytesread);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if(inputstream!=null) {
					try {
						inputstream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					outstream.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(outstream!=null) {
					try {
						outstream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
		
		}
		return"redirect:/student/";
	}
	@GetMapping("/showquiz/{id}")
	public String showquiz(@PathVariable("id") int id,Model m) {
		Quiz quiz = quizRepository.findById(id);
		m.addAttribute("quiz", quiz);
		return"student/showquiz";
	}
	@PostMapping("/submitquiz")
	public String processquiz(@RequestParam("questionId") String []questionIdes,HttpServletRequest request) {
		int score=0;
		for (String questionId : questionIdes) {
	
			int answerIdcorrect=questionService.findAnsweidCorrect(Integer.parseInt(questionId));
			if (answerIdcorrect==Integer.parseInt(request.getParameter("question_"+questionId))) {
				score++;
			}
		}
		System.out.println(score);
		return "student/result";
	}
	}	

