package ru.itis.mappers;

import org.mapstruct.Mapper;
import ru.itis.dto.UserDetailsDto;
import ru.itis.dto.parent.NewParentDto;
import ru.itis.dto.student.NewOrUpdateStudentDto;
import ru.itis.dto.teacher.NewOrUpdateTeacherDto;
import ru.itis.models.UserDetails;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper {
    UserDetails toUser(NewOrUpdateStudentDto studentDto);

    UserDetails toUser(NewParentDto parent);

    UserDetails toUser(NewOrUpdateTeacherDto teacher);

    UserDetailsDto toDto(UserDetails userDetails);
}
