package pl.sda.arppl4.school.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.school.model.Student;
import pl.sda.arppl4.school.model.dto.StudentDTO;
import pl.sda.arppl4.school.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<StudentDTO> findAll() {
        List<Student> studentList = studentRepository.findAll();

        List<StudentDTO> students = new ArrayList<>();
        for (Student student : studentList) {
            students.add(student.mapToStudentDTO());
        }

        return students;
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public void update(Long studentId, Student editStudentInfromation) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            if (editStudentInfromation.getNameStudent() != null) {
                student.setNameStudent(editStudentInfromation.getNameStudent());
            }
            if (editStudentInfromation.getNameStudent() != null) {
                student.setNameStudent(editStudentInfromation.getSurName());
            }
            if (editStudentInfromation.getBirthDay() != null) {
                student.setBirthDay(editStudentInfromation.getBirthDay());
            }
            if (editStudentInfromation.getIndexNumber() != null) {
                student.setIndexNumber(editStudentInfromation.getIndexNumber());
            }

            studentRepository.save(student);
            return;
        }
        throw new EntityNotFoundException("Unable to find student with id: " + studentId);
    }

}


