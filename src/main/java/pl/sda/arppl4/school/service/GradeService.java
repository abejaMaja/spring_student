package pl.sda.arppl4.school.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.school.model.Grade;
import pl.sda.arppl4.school.model.Student;
import pl.sda.arppl4.school.model.dto.GradeRequest;
import pl.sda.arppl4.school.model.dto.StudentDTO;
import pl.sda.arppl4.school.repository.GradeRepository;
import pl.sda.arppl4.school.repository.StudentRepository;
import pl.sda.arppl4.school.repository.SubjectRepository;

import pl.sda.arppl4.school.model.Subject;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;


    public List<Grade> getAllgrades(Long studenentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studenentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            List<Grade> grades = new ArrayList<>(student.getGrades());
            return grades;

        }
        throw new EntityNotFoundException("Unable to find student with id: " + studenentId);

    }

    public void addGrade(Long subjectId, Long studentId, GradeRequest request) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);

        if (studentOptional.isPresent()) {

            if (subjectOptional.isPresent()) {
                Student student = studentOptional.get();
                Subject subject = subjectOptional.get();
                Grade grade = mapGradeRequestToGrade(request);
                grade.setStudent(student);
                grade.setValue(grade.getValue());
                grade.setSubject(subject);

                gradeRepository.save(grade);
            }
            throw new EntityNotFoundException("Unable to find subject with id: " + subjectId);
        }
        throw new EntityNotFoundException("Unable to find car with id: " + studentId);
    }

    private Grade mapGradeRequestToGrade(GradeRequest request) {
        return new Grade(request.getValue());

    }


    public List<Grade> getGradesPerSubject(Long studentId, Long subjectId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if (subjectOptional.isPresent()) {
                Subject subject = subjectOptional.get();
                List<Grade> grades = new ArrayList<>(student.getGrades());
                return grades;

            }
            throw new EntityNotFoundException("Unable to find subject with id: " + subjectId);
        }
        throw new EntityNotFoundException("Unable to find car with id: " + studentId);
    }
}