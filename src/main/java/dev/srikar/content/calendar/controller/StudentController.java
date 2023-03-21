package dev.srikar.content.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.srikar.content.calendar.model.Student.Student;
import dev.srikar.content.calendar.service.StudentService;

import java.util.List;


@RestController
@Validated
@RequestMapping(path="api/v1/student")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

    @GetMapping("/all")
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

	@PostMapping("/register")
	public List<Student> registerNewStudent(@RequestBody Student student) {
		studentService.AddNewStudent(student);
		return studentService.getStudents(); 
	}

	@DeleteMapping("{id}")
	public List<Student> deleteStudent(@PathVariable("id") Long id) {
		studentService.DeleteById(id);
		return studentService.getStudents();
	}
    
	@PutMapping("{id}")
	public List<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
		studentService.UpdateById(id, student);
		return studentService.getStudents();
	}
}
