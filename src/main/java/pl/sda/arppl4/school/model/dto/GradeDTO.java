package pl.sda.arppl4.school.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.arppl4.school.model.Student;
import pl.sda.arppl4.school.model.Subject;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {

    private Long gradeId;
    private Double value;
    private Student student;
    private Subject subject;

}
