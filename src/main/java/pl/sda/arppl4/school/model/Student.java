package pl.sda.arppl4.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.sda.arppl4.school.model.dto.StudentDTO;
import pl.sda.arppl4.school.model.dto.StudentRequest;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameStudent;
    private String surName;
    private LocalDate birthDay;
    private String indexNumber;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude

    private Set<Grade> grades;

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public Student(StudentRequest request) {
        this.nameStudent = request.getNameStudent();
        this.surName = request.getSurName();
        this.birthDay = request.getBirthDay();
        this.indexNumber = request.getIndexNumber();
    }

    public StudentDTO mapToStudentDTO() {
        return new StudentDTO(
                id,
                nameStudent,
                surName,
                birthDay,
                indexNumber
        );
    }

}
