package pl.sda.arppl4.school.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import pl.sda.arppl4.school.model.dto.GradeDTO;
import pl.sda.arppl4.school.model.dto.StudentDTO;
import pl.sda.arppl4.school.model.dto.SubjectDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;
    @CreationTimestamp
    private LocalDateTime addData;

    @ManyToOne()
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Student student;

    @ManyToOne()
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Subject subject;

    public Grade(Double value) {
        this.value = value;

    }


    public GradeDTO mapToGradeDTO() {
        return new GradeDTO(
                id,
                value,
                student,
                subject

        );
    }


}
