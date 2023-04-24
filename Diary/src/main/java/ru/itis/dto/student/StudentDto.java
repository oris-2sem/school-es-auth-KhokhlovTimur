package ru.itis.dto.student;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.dto.UserDetailsDto;
import ru.itis.dto.parent.ParentDto;
import ru.itis.dto.group.GroupDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {
    Long id;
    String firstName;
    String lastName;
    Integer age;
    GroupDto group;
    ParentDto parent;
    UserDetailsDto userDetails;
}
