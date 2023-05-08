package ru.itis.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.lesson.LessonDto;
import ru.itis.dto.lesson.NewOrUpdateLessonDto;
import ru.itis.dto.task.NewOrUpdateTaskDto;
import ru.itis.dto.task.TaskDto;
import ru.itis.dto.timetable.TimetableRowsPage;
import ru.itis.services.lessons.LessonsService;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LessonsController {
    LessonsService lessonsService;

    @PostMapping
    public ResponseEntity<LessonDto> add(@RequestBody NewOrUpdateLessonDto lessonDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(lessonsService.add(lessonDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonDto> update(@PathVariable("id") Long id,
                                          @RequestBody NewOrUpdateLessonDto lessonDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(lessonsService.update(id, lessonDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        lessonsService.delete(id);
        return ResponseEntity.accepted()
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(lessonsService.findDtoById(id));
    }

    @GetMapping("/{id}/timetable")
    public ResponseEntity<TimetableRowsPage> getRows(@PathVariable("id") Long id, @RequestParam("page") int pageNumber) {
        return ResponseEntity.ok(lessonsService.getRows(id, pageNumber));
    }
}
