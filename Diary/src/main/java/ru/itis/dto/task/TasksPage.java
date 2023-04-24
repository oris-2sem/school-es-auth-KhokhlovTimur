package ru.itis.dto.task;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.itis.dto.task.TaskDto;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TasksPage {
    List<TaskDto> tasks;
    Integer pagesCount;
}
