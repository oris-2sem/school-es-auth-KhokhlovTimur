package ru.itis.services.tasks;

import ru.itis.dto.task.NewOrUpdateTaskDto;
import ru.itis.dto.task.TaskDto;

public interface TasksService {
    TaskDto add(NewOrUpdateTaskDto taskDto);

    TaskDto findById(Long id);

    void delete(Long id);

    TaskDto update(Long id, NewOrUpdateTaskDto taskDto);

}
