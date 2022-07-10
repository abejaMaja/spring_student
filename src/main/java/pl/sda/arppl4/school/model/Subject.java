package pl.sda.arppl4.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.sda.arppl4.school.model.dto.StudentDTO;
import pl.sda.arppl4.school.model.dto.SubjectDTO;
import pl.sda.arppl4.school.model.dto.SubjectRequest;


import javax.persistence.*;
import java.util.Set;

 // Getter Setter ToString EqualsAndHashCode
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameSubject;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Set<Grade> grades;

     public Subject(String nameSubject) {
         this.nameSubject = nameSubject;
     }

     public SubjectDTO mapToSubjectDTO() {
        return new SubjectDTO(
                id,
                nameSubject

        );
    }

}
