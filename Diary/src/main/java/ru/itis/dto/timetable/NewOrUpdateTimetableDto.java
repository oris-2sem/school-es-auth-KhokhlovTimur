package ru.itis.dto.timetable;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.itis.dto.group.GroupDto;
import ru.itis.dto.lesson.LessonDto;
import ru.itis.dto.teacher.TeacherDto;

import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewOrUpdateTimetableDto {
    LocalTime startTime;
    String room;
    Long groupId;
    Long teacherId;
    Long lessonId;
}
