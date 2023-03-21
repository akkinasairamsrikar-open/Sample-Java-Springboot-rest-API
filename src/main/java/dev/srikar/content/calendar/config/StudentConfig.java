package dev.srikar.content.calendar.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import dev.srikar.content.calendar.model.Student.Student;
import dev.srikar.content.calendar.repositories.StudentRepository;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student srikar = new Student(
                "Srikar",
                "akkina@gmail.com",
                21
            );
            Student ram = new Student(
                "Ram",
                "ram@gmail.com",
                23
            );
            repository.saveAll(
                List.of(srikar, ram)
            );
        };      
    };         
}
