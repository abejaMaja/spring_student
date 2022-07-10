package pl.sda.arppl4.school.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.school.model.Grade;
import pl.sda.arppl4.school.model.Student;
import pl.sda.arppl4.school.model.dto.GradeDTO;
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


    public List<GradeDTO> getAllgrades(Long studenentId) {

        Optional<Student> optionalStudent = studentRepository.findById(studenentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            List<Grade> grades = new ArrayList<>(student.getGrades());
            List<GradeDTO> gradDTO = new ArrayList<>();
            for(Grade grade: grades){
                gradDTO.add(grade.mapToGradeDTO());
            }
            return gradDTO;

        }
        throw new EntityNotFoundException("Unable to find student with id: " + studenentId);

    }

    public void addGrade(Long subjectId, Long studentId, GradeRequest request) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);

        if (studentOptional.isPresent() && subjectOptional.isPresent()) {

            Student student = studentOptional.get();
            log.info("Studnet o id " + student.getNameStudent() );
            Subject subject = subjectOptional.get();
            log.info("Studnet o id " + subject.getNameSubject() );
            Grade gradeNew =new Grade();
            gradeNew.setStudent(student);
            gradeNew.setValue(request.getValue());
            gradeNew.setSubject(subject);

            gradeRepository.save(gradeNew);


        }else {
            throw new EntityNotFoundException("Unable to find student and subject with id: " + studentId + " , " + subjectId);
        }

    }

    public List<GradeDTO> getGradesPerSubject(Long subjectId, Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if (subjectOptional.isPresent()) {
                Subject subject = subjectOptional.get();
                List<Grade> grades = new ArrayList<>(student.getGrades());
                List<GradeDTO> gradDTO = new ArrayList<>();
                for(Grade grade: grades){
                    gradDTO.add(grade.mapToGradeDTO());
                }
                return gradDTO;

            }
            throw new EntityNotFoundException("Unable to find subject with id: " + subjectId);
        }
        throw new EntityNotFoundException("Unable to find student with id: " + studentId);
    }

    public void update(Long gradeId, GradeRequest request) {
        Optional<Grade> gradeOptional = gradeRepository.findById(gradeId);
        if(gradeOptional.isPresent()){
            Grade grade = gradeOptional.get();
            if(request.getValue()!=null){
                grade.setValue(request.getValue());
            }

        }

    }
}