package ru.itis.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.task.NewOrUpdateTaskDto;
import ru.itis.dto.task.TaskDto;
import ru.itis.services.tasks.TasksService;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TasksController {
    TasksService tasksService;

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tasksService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TaskDto> add(@RequestBody NewOrUpdateTaskDto taskDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tasksService.add(taskDto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable("id") Long id,
                                          @RequestBody NewOrUpdateTaskDto taskDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(tasksService.update(id, taskDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        tasksService.delete(id);
        return ResponseEntity.accepted()
                .build();
    }
}
