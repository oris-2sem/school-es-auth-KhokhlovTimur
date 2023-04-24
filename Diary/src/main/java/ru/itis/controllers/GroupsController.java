package ru.itis.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.group.GroupDto;
import ru.itis.dto.group.NewOrUpdateGroupDto;
import ru.itis.dto.student.StudentsPage;
import ru.itis.services.groups.GroupsStudentsService;
import ru.itis.services.groups.GroupsService;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GroupsController {
    GroupsService groupsService;
    GroupsStudentsService groupsStudentsService;

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(groupsService.findDtoById(id));
    }

    @PostMapping
    public ResponseEntity<GroupDto> add(@RequestBody NewOrUpdateGroupDto groupDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(groupsService.add(groupDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> update(@PathVariable("id") Long id,
                                           @RequestBody NewOrUpdateGroupDto groupDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(groupsService.update(id, groupDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        groupsService.delete(id);
        return ResponseEntity.accepted()
                .build();
    }

    @PostMapping("/{group_id}/students/{student_id}")
    public ResponseEntity<StudentsPage> addStudent(@PathVariable("group_id") Long groupId,
                                                   @PathVariable("student_id") Long studentId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(groupsStudentsService.addStudent(groupId, studentId));
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<StudentsPage> getStudents(@PathVariable("id") Long id) {
        return ResponseEntity.ok(groupsStudentsService.getStudentsFromGroup(id));
    }
}
