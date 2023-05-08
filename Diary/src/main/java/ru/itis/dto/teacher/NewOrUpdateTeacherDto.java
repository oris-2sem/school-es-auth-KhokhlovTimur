package ru.itis.dto.teacher;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewOrUpdateTeacherDto {
    String firstName;
    String lastName;
    Integer age;
    Integer experience;
    String login;
    String password;
}
