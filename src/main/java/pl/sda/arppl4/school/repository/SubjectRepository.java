package pl.sda.arppl4.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.sda.arppl4.school.model.Subject;


public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
