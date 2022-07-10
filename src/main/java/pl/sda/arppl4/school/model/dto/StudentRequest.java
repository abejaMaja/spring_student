package pl.sda.arppl4.school.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    private String nameStudent;
    private String surName;
    private LocalDate birthDay;
    private String indexNumber;

}
