package pl.sda.arppl4.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.arppl4.school.model.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {
}
