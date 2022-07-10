package pl.sda.arppl4.school.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.school.exception.CustomException;
import pl.sda.arppl4.school.model.Grade;
import pl.sda.arppl4.school.model.Student;
import pl.sda.arppl4.school.model.dto.StudentDTO;
import pl.sda.arppl4.school.model.dto.StudentRequest;
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

    public void addStudent(StudentRequest request) {
        Student newStudent = new Student(request);
        studentRepository.save(newStudent);
    }

    public void deleteStudent(Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(optionalStudent.isPresent()){
            Student student = optionalStudent.get();
           List<Grade> gradeList = new ArrayList<>(student.getGrades()) ;
           if(gradeList.isEmpty()){
               studentRepository.deleteById(studentId);

           }else {
               throw new CustomException("There are grades, you are not able to delete students with grades");
           }

        }else {
            throw new EntityNotFoundException("Unable to find student with id: " + studentId);
        }


    }

    public void update(Long studentId, StudentRequest request) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            if (request.getNameStudent() != null) {
                student.setNameStudent(request.getNameStudent());
            }
            if (request.getNameStudent() != null) {
                student.setNameStudent(request.getSurName());
            }
            if (request.getBirthDay() != null) {
                student.setBirthDay(request.getBirthDay());
            }
            if (request.getIndexNumber() != null) {
                student.setIndexNumber(request.getIndexNumber());
            }

            studentRepository.save(student);
            return;
        }
        throw new EntityNotFoundException("Unable to find student with id: " + studentId);
    }

    public void addStudentList(List<Student> list) {
        List<Student> newList = new ArrayList<>();
        for (Student student:list){
            newList.add(student);
        }

        studentRepository.saveAll(newList);
    }
}


