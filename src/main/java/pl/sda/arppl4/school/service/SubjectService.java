package pl.sda.arppl4.school.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.school.exception.CustomException;
import pl.sda.arppl4.school.model.dto.SubjectDTO;
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

    public void updateSubject(Long subjectId, Subject editSubjectInformation) {
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
        if (subjectOptional.isPresent()) {
            Subject subject = subjectOptional.get();

            if(editSubjectInformation.getNameSubject()!=null){
                subject.setNameSubject(editSubjectInformation.getNameSubject());
            }


            subjectRepository.save(subject);
            return;
        }
        throw new EntityNotFoundException("Unable to find subject with id: " + subjectId);
    }

    public List<SubjectDTO> findAllSubject() {
        List<Subject> subjectList = subjectRepository.findAll();

        List<SubjectDTO> subjects = new ArrayList<>();
        for (Subject subject : subjectList) {
            subjects.add(subject.mapToSubjectDTO());
        }

        return subjects;
    }

    public void addSubject(Subject subject) {

        List<Subject> subjectLista = subjectRepository.findAll();

        if ( subjectLista != null) {
            for (Subject mySubject : subjectLista) {
                String correctedName = mySubject.getNameSubject().replaceAll(" ", "");
                if (!correctedName.equalsIgnoreCase(subject.toString())) {
                    subjectRepository.save(subject);
                    return;
                }
                throw new CustomException("This subject already exist: " + subject.getNameSubject());

            }
        }else{
            subjectRepository.save(subject);
        }



    }
}
