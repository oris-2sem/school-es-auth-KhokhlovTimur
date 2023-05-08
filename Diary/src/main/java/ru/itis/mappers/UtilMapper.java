package ru.itis.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.student.StudentDto;
import ru.itis.models.Student;

@Mapper(componentModel = "spring")
public interface UtilMapper {
    @Mapping(target = "group", ignore = true)
    @Mapping(target = "parent", ignore = true)
    StudentDto studentToStudentDto(Student student);
}
