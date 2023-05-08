package ru.itis.dto.group;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.itis.dto.teacher.TeacherDto;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewOrUpdateGroupDto {
    Date dateOfAdmission;
    String letter;
    Long teacherId;
}
