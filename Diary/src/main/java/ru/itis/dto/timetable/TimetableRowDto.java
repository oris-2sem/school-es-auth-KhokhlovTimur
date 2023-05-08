package ru.itis.dto.timetable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.itis.dto.group.GroupDto;
import ru.itis.dto.lesson.LessonDto;
import ru.itis.dto.teacher.TeacherDto;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimetableRowDto {
    Long id;
    LocalTime startTime;
    String room;
    GroupDto group;
    TeacherDto teacher;
    LessonDto lesson;
}
