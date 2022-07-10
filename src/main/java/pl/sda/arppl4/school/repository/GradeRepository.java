package pl.sda.arppl4.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.arppl4.school.model.Grade;


public interface GradeRepository extends JpaRepository<Grade, Long> {
}
