package ru.itis.dto.student;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.itis.dto.student.StudentDto;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentsPage {
    Set<StudentDto> students;
    Integer totalCount;
}
