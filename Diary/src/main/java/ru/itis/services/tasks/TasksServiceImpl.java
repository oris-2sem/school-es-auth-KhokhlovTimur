package ru.itis.services.tasks;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.itis.dto.task.NewOrUpdateTaskDto;
import ru.itis.dto.task.TaskDto;
import ru.itis.exceptions.NotFoundException;
import ru.itis.mappers.TasksMapper;
import ru.itis.models.Lesson;
import ru.itis.models.Student;
import ru.itis.models.Task;
import ru.itis.repositories.TasksRepository;
import ru.itis.services.lessons.LessonsService;
import ru.itis.services.students.StudentsService;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TasksServiceImpl implements TasksService {
    TasksRepository tasksRepository;
    TasksMapper tasksMapper;
    StudentsService studentsService;
    LessonsService lessonsService;

    @Override
    public TaskDto add(NewOrUpdateTaskDto taskDto) {
        Task task = tasksRepository.save(tasksMapper.toTask(taskDto));

        Student student = studentsService.findById(taskDto.getStudentId());
        Lesson lesson = lessonsService.findById(taskDto.getLessonId());

        task.setLesson(lesson);
        task.setStudent(student);

        student.getTasks().add(task);
        lesson.getTasks().add(task);

        return tasksMapper.toDto(tasksRepository.save(task));
    }

    @Override
    public TaskDto findById(Long id) {
        return tasksMapper.toDto(getOrThrow(id));
    }

    @Override
    public void delete(Long id) {
        tasksRepository.delete(getOrThrow(id));
    }

    @Override
    public TaskDto update(Long id, NewOrUpdateTaskDto taskDto) {
        Task task = getOrThrow(id);

        Student newStudent = null;
        Lesson newLesson = null;
        if (taskDto.getStudentId() != null) {
            newStudent = studentsService.findById(taskDto.getStudentId());
        }
        if (taskDto.getLessonId() != null) {
            newLesson = lessonsService.findById(taskDto.getLessonId());
        }

        task.setComment(taskDto.getComment());
        task.setDescription(taskDto.getDescription());
        task.setMark(taskDto.getMark());
        task.setStudent(newStudent);
        task.setLesson(newLesson);

        tasksRepository.save(task);
        return tasksMapper.toDto(task);
    }

    private Task getOrThrow(Long id) {
        return tasksRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id <" + id + "> not found"));
    }
}
