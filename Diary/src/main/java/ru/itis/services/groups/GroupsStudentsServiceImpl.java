package ru.itis.services.groups;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.itis.dto.student.StudentDto;
import ru.itis.dto.student.StudentsPage;
import ru.itis.mappers.GroupsMapper;
import ru.itis.models.Group;
import ru.itis.models.Student;
import ru.itis.repositories.GroupsRepository;
import ru.itis.services.students.StudentsService;

import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GroupsStudentsServiceImpl implements GroupsStudentsService {
    StudentsService studentsService;
    GroupsService groupsService;
    GroupsRepository groupsRepository;
    GroupsMapper groupsMapper;

    @Override
    public StudentsPage addStudent(Long groupId, Long studentId) {
        Group group = groupsService.findById(groupId);
        Student student = studentsService.findById(studentId);

        group.getStudents().add(student);
        student.setGroup(group);
        groupsRepository.save(group);

        Set<StudentDto> students = groupsMapper.toStudentDtoSet(group.getStudents());

        return StudentsPage.builder()
                .students(students)
                .totalCount(students.size())
                .build();
    }

    @Override
    public StudentsPage getStudentsFromGroup(Long groupId) {
        Group group = groupsService.findById(groupId);

        Set<StudentDto> students = groupsMapper.toStudentDtoSet(group.getStudents());
        return StudentsPage.builder()
                .students(students)
                .totalCount(students.size())
                .build();
    }
}
