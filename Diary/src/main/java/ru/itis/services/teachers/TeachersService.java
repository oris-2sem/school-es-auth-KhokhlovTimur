package ru.itis.services.teachers;

import ru.itis.dto.teacher.NewOrUpdateTeacherDto;
import ru.itis.dto.teacher.TeacherDto;
import ru.itis.dto.timetable.TimetableRowsPage;
import ru.itis.models.Teacher;

public interface TeachersService {
    Teacher findById(Long id);

    TeacherDto findDtoById(Long id);

    TeacherDto add(NewOrUpdateTeacherDto teacherDto);

    void delete(Long id);

    TeacherDto update(Long id, NewOrUpdateTeacherDto teacherDto);

    TimetableRowsPage getRows(Long id);
}
