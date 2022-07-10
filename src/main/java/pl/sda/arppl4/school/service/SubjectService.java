package pl.sda.arppl4.school.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.school.exception.CustomException;
import pl.sda.arppl4.school.model.Student;
import pl.sda.arppl4.school.model.dto.SubjectDTO;
import pl.sda.arppl4.school.model.dto.SubjectRequest;
import pl.sda.arppl4.school.repository.SubjectRepository;


import javax.persistence.EntityNotFoundException;

import pl.sda.arppl4.school.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;


    public void deleteSubject(Long subjectId) {
        subjectRepository.deleteById(subjectId);
    }


    public List<SubjectDTO> findAllSubject() {
        List<Subject> subjectList = subjectRepository.findAll();

        List<SubjectDTO> subjects = new ArrayList<>();
        for (Subject subject : subjectList) {
            subjects.add(subject.mapToSubjectDTO());
        }

        return subjects;
    }

    public void addSubject(SubjectRequest request) {
        log.info("my subject 1  " + request.getNameSubject());
        List<Subject> subjectLista = subjectRepository.findAll();
        log.info("my subject  " + request.getNameSubject());
        log.info("my subject list is " + subjectLista);

        if (!subjectLista.isEmpty()) {
            for (Subject mySubject : subjectLista) {
                String correctedName = mySubject.getNameSubject().replaceAll(" ", "");
                String requestCorrectedName = request.getNameSubject().replaceAll(" ", "");
                log.info("my request corrected subject name is " + requestCorrectedName);
                if (correctedName.equalsIgnoreCase(requestCorrectedName)) {
                    throw new CustomException("This subject already exist: " + request.getNameSubject());

                }
            }

        }
        Subject newSubject = new Subject(request.getNameSubject());
        subjectRepository.save(newSubject);


    }
}
