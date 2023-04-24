package ru.itis.mappers;

import org.mapstruct.Mapper;
import ru.itis.dto.student.NewOrUpdateStudentDto;
import ru.itis.dto.student.StudentDto;
import ru.itis.models.Student;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface StudentsMapper {
    StudentDto toDto(Student student);

    Student toStudent(NewOrUpdateStudentDto studentDto);

    Student toStudent(StudentDto studentDto);

}
