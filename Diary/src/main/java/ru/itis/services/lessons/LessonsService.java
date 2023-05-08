package ru.itis.services.lessons;

import ru.itis.dto.lesson.NewOrUpdateLessonDto;
import ru.itis.dto.lesson.LessonDto;
import ru.itis.dto.task.TasksPage;
import ru.itis.dto.timetable.TimetableRowsPage;
import ru.itis.models.Lesson;

public interface LessonsService {
    LessonDto add(NewOrUpdateLessonDto lessonDto);

    LessonDto update(Long id, NewOrUpdateLessonDto lessonDto);

    void delete(Long id);

    LessonDto findDtoById(Long id);

    Lesson findById(Long id);

    TasksPage getTasks(Long id, int pageNumber);

    TimetableRowsPage getRows(Long id, int pageCount);
}
