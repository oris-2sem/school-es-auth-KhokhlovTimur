package ru.itis.mappers;

import org.mapstruct.Mapper;
import ru.itis.dto.parent.NewParentDto;
import ru.itis.dto.parent.ParentDto;
import ru.itis.dto.student.StudentDto;
import ru.itis.models.Parent;
import ru.itis.models.Student;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ParentsMapper {
    ParentDto toDto(Parent parent);

    Parent toParent(NewParentDto parentDto);
}
