package pl.sda.arppl4.school.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.school.model.Student;
import pl.sda.arppl4.school.model.dto.StudentDTO;
import pl.sda.arppl4.school.service.StudentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor

public class StudentController {
    private final StudentService studentService;


    @GetMapping()
    public ResponseEntity<List<StudentDTO>> list() {
        log.info("Received request: list");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.findAll());
    }

    @PostMapping(value="/rest/account/json", consumes={"application/json"})
    @ResponseStatus(HttpStatus.CREATED)

    public void create(@RequestBody Student student) {
        log.info("Received request: create -> " + student);
        studentService.addStudent(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long studentId) {
        log.info("Received request: delete -> " + studentId);
        studentService.deleteStudent(studentId);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable(name = "id") Long studentId, @RequestBody Student student) {
        log.info("Received request: update -> " + student);
        studentService.update(studentId, student);
    }
}
