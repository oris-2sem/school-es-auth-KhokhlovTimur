package ru.itis.mappers;

import org.mapstruct.Mapper;
import ru.itis.dto.teacher.NewOrUpdateTeacherDto;
import ru.itis.dto.teacher.TeacherDto;
import ru.itis.models.Teacher;

@Mapper(componentModel = "spring")
public interface TeachersMapper {
    TeacherDto toDto(Teacher teacher);

    Teacher toTeacher(TeacherDto teacherDto);

    Teacher toTeacher(NewOrUpdateTeacherDto teacherDto);
}
