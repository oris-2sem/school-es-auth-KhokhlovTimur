package ru.itis.mappers;

import org.mapstruct.*;
import ru.itis.dto.student.StudentDto;
import ru.itis.dto.task.NewOrUpdateTaskDto;
import ru.itis.dto.task.TaskDto;
import ru.itis.models.Student;
import ru.itis.models.Task;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = UtilMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TasksMapper {
    TaskDto toDto(Task task);

    Task toTask(TaskDto taskDto);

    Task toTask(NewOrUpdateTaskDto taskDto);

    @IterableMapping(qualifiedByName = "withoutStudent")
    List<TaskDto> toDtoList(List<Task> tasks);

    @Named("withoutStudent")
    @Mapping(target = "student", ignore = true)
    TaskDto toDtoWithoutStudent(Task task);
}
