package ru.itis.dto.group;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.itis.dto.teacher.TeacherDto;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupDto {
    Long id;
    Date dateOfAdmission;
    String letter;
    TeacherDto teacher;
}
