package ru.itis.services.lessons;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.dto.lesson.NewOrUpdateLessonDto;
import ru.itis.dto.lesson.LessonDto;
import ru.itis.dto.task.TasksPage;
import ru.itis.dto.timetable.TimetableRowsPage;
import ru.itis.exceptions.NotFoundException;
import ru.itis.mappers.LessonsMapper;
import ru.itis.models.Lesson;
import ru.itis.models.Task;
import ru.itis.models.TimetableRow;
import ru.itis.repositories.LessonsRepository;
import ru.itis.repositories.TasksRepository;
import ru.itis.repositories.TimetableRowsRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonsServiceImpl implements LessonsService {
    final LessonsRepository lessonsRepository;
    final LessonsMapper lessonsMapper;
    final TimetableRowsRepository timetableRowsRepository;
    final TasksRepository tasksRepository;
    @Value("${page-size}")
    int pageSize;

    @Override
    public LessonDto add(NewOrUpdateLessonDto lessonDto) {
        return lessonsMapper.toDto(lessonsRepository.save(lessonsMapper.toLesson(lessonDto)));
    }

    @Override
    public LessonDto update(Long id, NewOrUpdateLessonDto lessonDto) {
        Lesson lesson = getOrThrow(id);

        lesson.setSubject(lessonDto.getSubject());
        lesson.setTheme(lessonDto.getTheme());

        return lessonsMapper.toDto(lessonsRepository.save(lesson));
    }

    @Override
    public void delete(Long id) {
        lessonsRepository.delete(getOrThrow(id));
    }

    @Override
    public LessonDto findDtoById(Long id) {
        return lessonsMapper.toDto(getOrThrow(id));
    }

    @Override
    public Lesson findById(Long id) {
        return getOrThrow(id);
    }

    @Override
    public TasksPage getTasks(Long id, int pageNumber) {
        getOrThrow(id);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Task> tasks = tasksRepository.findAllByStudentIdOrderById(id, pageRequest);

        return TasksPage.builder()
                .tasks(lessonsMapper.toTaskDtoList(tasks.getContent()))
                .pagesCount(tasks.getTotalPages())
                .build();
    }

    @Override
    public TimetableRowsPage getRows(Long id, int pageCount) {
        getOrThrow(id);
        PageRequest pageRequest = PageRequest.of(pageCount, pageSize);
        Page<TimetableRow> page = timetableRowsRepository.findAllByLessonIdOrderById(pageRequest, id);

        return TimetableRowsPage.builder()
                .timetableRows(lessonsMapper.toTimetableRowsDtoList(page.getContent()))
                .pagesCount(page.getTotalPages())
                .build();
    }

    private Lesson getOrThrow(Long id) {
        return lessonsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Lesson with id <" + id + "> not found"));
    }
}
