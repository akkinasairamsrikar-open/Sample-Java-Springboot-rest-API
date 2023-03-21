package dev.srikar.content.calendar.service;
import dev.srikar.content.calendar.model.Student.*;
import dev.srikar.content.calendar.repositories.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import javax.transaction.Transactional;


@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	};
	
    public List<Student> getStudents() {
		return studentRepository.findAll();

	};

	public void AddNewStudent(Student student) {
		if (studentRepository.findStudentByEmail(student.getEmail()).isPresent()) {
			throw new IllegalStateException("Email taken");
		} else {
			studentRepository.save(student);
		}
	};

	public void DeleteById(Long id) {
		boolean exists = studentRepository.existsById(id);
		if (exists) {
			studentRepository.deleteById(id);
		} else {
			throw new IllegalStateException("Student with id " + id + " does not exist.");
		}
	};

	@Transactional
	public void UpdateById(Long id,Student student) {

		Student studentToUpdate = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Student with id " + id + " does not exist."));
		if (student.getName() != null && student.getName().length() > 0 && !student.getName().equals(studentToUpdate.getName())) {
			studentToUpdate.setName(student.getName());
		}
		if (student.getEmail() != null && student.getEmail().length() > 0 && !student.getEmail().equals(studentToUpdate.getEmail())) {
			studentToUpdate.setEmail(student.getEmail());
		}
		if (student.getAge() != null && student.getAge() > 0 && !student.getAge().equals(studentToUpdate.getAge())) {
			studentToUpdate.setAge(student.getAge());
		}

	}
}
