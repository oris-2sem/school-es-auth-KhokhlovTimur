package ru.itis.dto.student;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewOrUpdateStudentDto {
    String login;
    String password;
    String firstName;
    String lastName;
    Integer age;
    Long groupId;
    Long parentId;
}
