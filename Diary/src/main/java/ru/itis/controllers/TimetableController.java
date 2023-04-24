package ru.itis.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.timetable.NewOrUpdateTimetableDto;
import ru.itis.dto.timetable.TimetableRowDto;
import ru.itis.dto.timetable.TimetableRowsPage;
import ru.itis.services.timetable.TimetableService;

@RestController
@RequestMapping("/timetable")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TimetableController {
    TimetableService timetableService;

    @GetMapping
    ResponseEntity<TimetableRowsPage> getTimetable(@RequestParam("page") int pageNumber) {
        return ResponseEntity.ok(timetableService.getTimetable(pageNumber));
    }

    @PostMapping
    ResponseEntity<TimetableRowDto> add(@RequestBody NewOrUpdateTimetableDto timetableDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(timetableService.addTimetableRow(timetableDto));
    }
}
