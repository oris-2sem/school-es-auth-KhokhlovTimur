package ru.itis.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.parent.NewParentDto;
import ru.itis.dto.parent.ParentDto;
import ru.itis.dto.student.StudentsPage;
import ru.itis.services.parents.ParentsService;
import ru.itis.services.parents.StudentsParentsService;

@RestController
@RequestMapping("/parents")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ParentsController {
    ParentsService parentsService;
    StudentsParentsService studentsParentsService;

    @PostMapping
    public ResponseEntity<ParentDto> add(@RequestBody NewParentDto parentDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(parentsService.add(parentDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(parentsService.findDtoById(id));
    }

    @PostMapping("/{parent_id}/students/{student_id}")
    public ResponseEntity<StudentsPage> addStudent(@PathVariable("parent_id") Long parentId,
                                                   @PathVariable("student_id") Long studentId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentsParentsService.addChild(parentId, studentId));
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<StudentsPage> getStudents(@PathVariable("id") Long id) {
        return ResponseEntity.ok(studentsParentsService.getChildren(id));
    }

}
