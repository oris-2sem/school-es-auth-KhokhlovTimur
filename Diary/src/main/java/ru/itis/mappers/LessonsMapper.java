package ru.itis.mappers;

import org.mapstruct.*;
import ru.itis.dto.group.GroupDto;
import ru.itis.dto.lesson.NewOrUpdateLessonDto;
import ru.itis.dto.lesson.LessonDto;
import ru.itis.dto.student.StudentDto;
import ru.itis.dto.task.TaskDto;
import ru.itis.dto.timetable.TimetableRowDto;
import ru.itis.models.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = UtilMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LessonsMapper {
    LessonDto toDto(Lesson lesson);

    Lesson toLesson(LessonDto lessonDto);

    Lesson toLesson(NewOrUpdateLessonDto lessonDto);

    @Named("withoutLesson")
    @Mapping(target = "lesson", ignore = true)
    TimetableRowDto toTimetableDto(TimetableRow timetableRow);

    @Mapping(target = "teacher", ignore = true)
    GroupDto toGroupDto(Group group);

    @IterableMapping(qualifiedByName = "withoutLesson")
    List<TimetableRowDto> toTimetableRowsDtoList(List<TimetableRow> timetableRows);

    @Mapping(target = "lesson", ignore = true)
    TaskDto toTaskDto(Task task);

    List<TaskDto> toTaskDtoList(List<Task> tasks);
}
