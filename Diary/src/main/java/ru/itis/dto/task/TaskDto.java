package ru.itis.dto.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.itis.dto.lesson.LessonDto;
import ru.itis.dto.student.StudentDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDto {
    Long id;
    String comment;
    String description;
    StudentDto student;
    LessonDto lesson;
    String mark;
}
