package ru.itis.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.student.NewOrUpdateStudentDto;
import ru.itis.dto.student.StudentDto;
import ru.itis.dto.task.TasksPage;
import ru.itis.services.students.StudentsService;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class StudentsController {
    StudentsService studentsService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(studentsService.findDtoById(id));
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<TasksPage> getTasks(@PathVariable("id") Long id, @RequestParam("page") int pageNumber) {
        return ResponseEntity.ok(studentsService.getTasks(id, pageNumber));
    }

    @PostMapping
    public ResponseEntity<StudentDto> add(@RequestBody NewOrUpdateStudentDto studentDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentsService.add(studentDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> update(@PathVariable("id") Long id,
                                             @RequestBody NewOrUpdateStudentDto studentDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(studentsService.update(id, studentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        studentsService.delete(id);
        return ResponseEntity.accepted()
                .build();
    }
}
