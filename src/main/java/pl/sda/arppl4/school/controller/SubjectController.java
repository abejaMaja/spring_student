package pl.sda.arppl4.school.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.school.model.dto.SubjectDTO;
import pl.sda.arppl4.school.service.SubjectService;


import pl.sda.arppl4.school.model.Subject;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/subject")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;



    @GetMapping()
    public ResponseEntity<List<SubjectDTO>> list() {
        log.info("Received request: list");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subjectService.findAllSubject());
    }

    @PostMapping(value="/rest/account/json", consumes={"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Subject subject) {
        log.info("Received request: create -> " + subject);
        subjectService.addSubject(subject);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long subjectId) {
        log.info("Received request: delete -> " + subjectId);
        subjectService.deleteSubject(subjectId);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable(name = "id") Long subjectId, @RequestBody Subject subject) {
        log.info("Received request: update -> " + subject);
        subjectService.updateSubject(subjectId, subject);
    }

}
