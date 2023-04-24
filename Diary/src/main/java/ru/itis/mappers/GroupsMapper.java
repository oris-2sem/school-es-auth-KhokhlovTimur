package ru.itis.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.itis.dto.group.GroupDto;
import ru.itis.dto.group.NewOrUpdateGroupDto;
import ru.itis.dto.student.StudentDto;
import ru.itis.models.Group;
import ru.itis.models.Student;

import java.util.Set;

@Mapper(componentModel = "spring", uses = UtilMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GroupsMapper {

    GroupDto toDto(Group group);

    Group toGroup(GroupDto groupDto);

    Group toGroup(NewOrUpdateGroupDto groupDto);

    Set<StudentDto> toStudentDtoSet(Set<Student> students);
}
