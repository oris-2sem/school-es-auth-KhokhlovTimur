package ru.itis.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.student.NewOrUpdateStudentDto;
import ru.itis.dto.student.StudentDto;
import ru.itis.dto.teacher.NewOrUpdateTeacherDto;
import ru.itis.dto.teacher.TeacherDto;
import ru.itis.services.teachers.TeachersService;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TeachersController {
    TeachersService teachersService;

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(teachersService.findDtoById(id));
    }

    @PostMapping
    public ResponseEntity<TeacherDto> add(@RequestBody NewOrUpdateTeacherDto teacherDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(teachersService.add(teacherDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> update(@PathVariable("id") Long id,
                                             @RequestBody NewOrUpdateTeacherDto teacherDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(teachersService.update(id, teacherDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        teachersService.delete(id);
        return ResponseEntity.accepted()
                .build();
    }
}
