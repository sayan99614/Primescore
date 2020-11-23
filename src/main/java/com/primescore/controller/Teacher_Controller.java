package com.primescore.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.primescore.entities.Answer;
import com.primescore.entities.Assignment;
import com.primescore.entities.Classes;
import com.primescore.entities.Login;
import com.primescore.entities.Question;
import com.primescore.entities.Quiz;
import com.primescore.entities.Quiz_Schedule;
import com.primescore.entities.Teacher;
import com.primescore.repository.ClassesRepository;
import com.primescore.repository.QuestionRepository;
import com.primescore.repository.QuizRepository;
import com.primescore.repository.QuizScheduleRepository;
import com.primescore.repository.TeacherRepository;
import com.primescore.repository.UserRepository;

import javassist.expr.NewArray;

@Controller
@RequestMapping("/teacher")
public class Teacher_Controller {

	@Autowired
	UserRepository repository;
	@Autowired
	TeacherRepository trepository;
	@Autowired
	ClassesRepository cRepository;
	@Autowired
	QuizRepository quizRepository;
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	QuizScheduleRepository quizScheduleRepository;

	@GetMapping("/")
	public String teacherRegister(Model m, Principal principal) {
		String name = principal.getName();
		Teacher teacher = trepository.getTeacherByUsername(name);
		List<Classes> classes = teacher.getClasses();
		m.addAttribute("classes", classes);
		m.addAttribute("title", "primescore-teacher");
		m.addAttribute("user", teacher);
		m.addAttribute("quiz",new Quiz());
		return "teacher/teacherdash";
	}

	@GetMapping("/classcreateshow")
	public String classshow(Model model) {
		model.addAttribute("class", new Classes());
		return "teacher/createclass";
	}

	@PostMapping("/createclass")
	public String createclass(@Valid @ModelAttribute("class") Classes classes, BindingResult result, Model model,
			Principal principal) {
		if (result.hasErrors()) {
			return "teacher/createclass";
		}
		// create a string of uppercase and lowercase characters and numbers
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
		String name = principal.getName();
		Teacher teacher = trepository.getTeacherByUsername(name);
		classes.setClasscode(randomString);
		classes.setStatus(false);
		classes.setTeacher(teacher);
		cRepository.save(classes);
		Teacher t = new Teacher();
		List<Classes> list = new ArrayList<Classes>();
		list.add(classes);
		t.setClasses(list);
		return "redirect:/teacher/";
	}

	@GetMapping("/allclasses")
	public String allclasses(Model m, Principal principal) {
		m.addAttribute("title", "teacher-allclasses");
		String name = principal.getName();
		Teacher teacher = trepository.getTeacherByUsername(name);
		List<Classes> classes = teacher.getClasses();
		m.addAttribute("classes", classes);
		return "teacher/allclasses";
	}

	@GetMapping("/viewprofile/{name}")
	public String viewprofile(@PathVariable("name") String name, Model m) {
		Teacher teacher = trepository.getTeacherByUsername(name);
		m.addAttribute("teacher", teacher);
		return "teacher/profile";
	}

	@GetMapping("/class/{code}")
	public String singleclass(@PathVariable("code") String classcode, Model m) {
		Classes singleclass = cRepository.getClassByClasscode(classcode);
		m.addAttribute("class", singleclass);
		m.addAttribute("assihnment", new Assignment());
		return "teacher/singleclass";
	}

	@PostMapping("/editclass")
	public String editclass(@ModelAttribute("class") Classes classes, Principal principal) {
		String name = principal.getName();
		Teacher teacher = trepository.getTeacherByUsername(name);
		Classes editclass = cRepository.getClassByClasscode(classes.getClasscode());
		editclass.setName(classes.getName());
		editclass.setDescrip(classes.getDescrip());
		editclass.setTeacher(teacher);
		List<Classes> list = new ArrayList<Classes>();
		list.add(editclass);
		teacher.setClasses(list);
		cRepository.save(editclass);
		return "redirect:/teacher/class/" + editclass.getClasscode();
	}

	@GetMapping("/status/{cid}/{status}")
	public String lockclass(@PathVariable("cid") int id, @PathVariable("status") boolean status, Principal principal) {
		Classes classes = cRepository.findById(id);
		String name = principal.getName();
		Teacher teacher = trepository.getTeacherByUsername(name);
		classes.setStatus(status);
		List<Classes> list = new ArrayList<Classes>();
		list.add(classes);
		if (classes.getTeacher().getTeacherId() == teacher.getTeacherId()) {
			classes.setTeacher(teacher);
			teacher.setClasses(list);
			cRepository.save(classes);
			return "redirect:/teacher/class/" + classes.getClasscode();
		}
		return "redirect:/teacher/class/" + classes.getClasscode();
	}

	@PutMapping("/delete/{id}")
	public String deleteclass(@PathVariable int id, Principal principal) {
		String name = principal.getName();
		Classes classes = cRepository.findById(id);
		Teacher teacher = trepository.getTeacherByUsername(name);
		if (teacher.getTeacherId() == classes.getTeacher().getTeacherId()) {
			cRepository.delete(classes);
		}
		return "redirect:/teacher/";
	}

	@PostMapping("/giveassignment/{id}")
	public String giveassignment(@ModelAttribute("assignment") Assignment assignment,
			@RequestParam("file_url") MultipartFile file, @PathVariable int id) {
		Classes classes = cRepository.findById(id);
		try {
			if (file.isEmpty()) {
				System.out.println("file is empty");
			} else {
				assignment.setPath(file.getOriginalFilename());
				assignment.setUploadDate(new Date());

				File file2 = new ClassPathResource("static/assignment").getFile();
				Path path = Paths.get(file2.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("file uploded successfully");
			}
			assignment.setClassassignment(classes);
			List<Assignment> listassignment = new ArrayList<Assignment>();
			listassignment.add(assignment);
			classes.setAssignments(listassignment);
			cRepository.save(classes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/teacher/class/" + classes.getClasscode();
	}
	@PostMapping("/createquiz")
	public String createquiz(@ModelAttribute("quiz") Quiz quiz,Principal principle) {
		String email = principle.getName();
		Teacher teacher = trepository.getTeacherByUsername(email);
		quiz.setCreation_date(new Date());
		quiz.setTeacher(teacher);
		quizRepository.save(quiz);
		return "teacher/teacherdash";
	}
	@GetMapping("/allquiz")
	public String allquiz(Principal principal,Model model) {
		String name = principal.getName();
		Teacher teacher = trepository.getTeacherByUsername(name);
		List<Quiz> quiz = teacher.getQuiz();
		for (Quiz q : quiz) {
			boolean check = q.getQuestions().isEmpty();
			model.addAttribute("check",check);
		}
		model.addAttribute("quiz",quiz);
	return "teacher/allquiz";
	}
	@GetMapping("/addquestion/{id}")
	public String addQuestionsform(Principal principal,Model m,@PathVariable int id) {
		String name = principal.getName();
		Teacher teacher = trepository.getTeacherByUsername(name);
		Quiz quiz = quizRepository.findById(id);
		m.addAttribute("question", new Question());
		m.addAttribute("teacher", teacher);
		m.addAttribute("answer", new Answer());
		m.addAttribute("title","Teacher-AddQuestion");
		if(quiz.getTeacher().getTeacherId()==teacher.getTeacherId()) {
			m.addAttribute("quiz",quiz);
		}
		return "teacher/addquesion";
	}
	@PostMapping("/qun/{quiz_id}")
	public String addquestion(@RequestParam("content") String questioncontent,@RequestParam("answerContent1") String answer1,@RequestParam("answerContent2") String answer2,
			@RequestParam("answerContent3") String answer3,@RequestParam("answerContent4") String answer4,@RequestParam int correct
			,@PathVariable("quiz_id") int qid) {
		
		Quiz quiz = quizRepository.findById(qid);
		
		
		Question question=new Question();
		question.setContent(questioncontent);
		
		Answer ans1=new Answer();
		ans1.setQuestion(question);
		ans1.setAnswerContent(answer1);
		ans1.setQuiz_ans(quiz);
		
		Answer ans2=new Answer();
		ans2.setQuestion(question);
		ans2.setAnswerContent(answer2);
		ans2.setQuiz_ans(quiz);
		
		
		Answer ans3=new Answer();
		ans3.setQuestion(question);
		ans3.setAnswerContent(answer3);
		ans3.setQuiz_ans(quiz);
		
		Answer ans4=new Answer();
		ans4.setQuestion(question);
		ans4.setAnswerContent(answer4);
		ans4.setQuiz_ans(quiz);
		
		
		switch (correct) {
		case 1:
			ans1.setCorrect(true);
			ans2.setCorrect(false);
			ans3.setCorrect(false);
			ans4.setCorrect(false);
			break;
		case 2:
			ans1.setCorrect(false);
			ans2.setCorrect(true);
			ans3.setCorrect(false);
			ans4.setCorrect(false);
			break;
		case 3:
			ans1.setCorrect(false);
			ans2.setCorrect(false);
			ans3.setCorrect(true);
			ans4.setCorrect(false);
			break;
		case 4:
			ans1.setCorrect(false);
			ans2.setCorrect(false);
			ans3.setCorrect(false);
			ans4.setCorrect(true);
			break;
		}
		
		List<Answer> anslist=new ArrayList<Answer>();
		anslist.add(ans1);
		anslist.add(ans2);
		anslist.add(ans3);
		anslist.add(ans4);
		
		
		question.setAnswers(anslist);
		
		question.setQuiz_qn(quiz);
		List<Question>qqn=new ArrayList<Question>();
		qqn.add(question);
		quiz.setQuestions(qqn);
		
		questionRepository.save(question);
		
		return"redirect:/teacher/addquestion/"+qid;	
	}
	
	@GetMapping("/preview/{qid}")
	public String prwviewquiz(@PathVariable("qid") int id,Model m) {
		Quiz quiz = quizRepository.findById(id);
		List<Question> questions = quiz.getQuestions();
		for (Question question : questions) {
			System.out.println(question.getContent());
		}
		m.addAttribute("quiz",quiz);
		return"teacher/seequiz";
	}
	@PostMapping("/assignquiz/{id}")
	
	public String assignQuiz(Principal principal,@RequestParam("classname") String name,@PathVariable int id) {
		Classes classes = cRepository.getClassByClassName(name);
		Quiz quiz = quizRepository.findById(id);
		Quiz_Schedule quizSchedule=new Quiz_Schedule();
		quizSchedule.setClasses(classes);
		quizSchedule.setQuiz(quiz);
		quizScheduleRepository.save(quizSchedule);
		
		String name2 = principal.getName();
		Teacher teacher = trepository.getTeacherByUsername(name2);
		return"";
	}
}
