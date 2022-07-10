package pl.sda.arppl4.school.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.school.model.Grade;
import pl.sda.arppl4.school.model.dto.GradeRequest;
import pl.sda.arppl4.school.service.GradeService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/grade")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;

    @GetMapping("/available")
    public List<Grade> getAllGrades(@RequestParam Long studentId) {
        log.info("Requested grades avaible");
        return gradeService.getAllgrades(studentId);
    }

    @GetMapping("/gradePerSubject")
    public List<Grade> getGradesPerSubject(@RequestParam Long studentId,  @RequestParam Long subjectId) {
        log.info("Requested grades per subject avaible");
        return gradeService.getGradesPerSubject(studentId, subjectId);
    }

    @PostMapping("/add/{idS}/{idP}")
    public void addGrade(@PathVariable(name = "idS")Long subjectId, @PathVariable(name = "idP") Long studentId, @RequestBody GradeRequest request) {
        log.info("Requested add grades: " + subjectId + studentId);
        gradeService.addGrade(subjectId, studentId, request);
    }



}
