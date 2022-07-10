package pl.sda.arppl4.school.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.school.model.dto.GradeDTO;
import pl.sda.arppl4.school.model.dto.GradeRequest;
import pl.sda.arppl4.school.service.GradeService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/grade")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;

    @GetMapping("/available/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<GradeDTO> getAllGrades(@PathVariable(name = "id") Long studentId) {
        log.info("Requested grades avaible");
        return gradeService.getAllgrades(studentId);
    }

    public List<GradeDTO> getGradesPerSubject(@PathVariable(name = "idS") Long subjectId,  @PathVariable(name = "idP") Long studentId) {
        log.info("Requested grades per subject avaible");
        return gradeService.getGradesPerSubject(subjectId, subjectId);
    }

    @PostMapping("/add/{idS}/{idP}")
    public void addGrade(@PathVariable(name = "idS")Long studentId, @PathVariable(name = "idP") Long subjectId, @RequestBody GradeRequest request) {
        log.info("Requested add grades: " + studentId + " , " + subjectId);
        gradeService.addGrade(subjectId, studentId, request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable(name = "id") Long gradeId, @RequestBody GradeRequest request) {
        log.info("Received request: update -> " + request);
        gradeService.update(gradeId, request);
    }



}
