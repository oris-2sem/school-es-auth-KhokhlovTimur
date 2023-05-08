package ru.itis.services.parents;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.itis.dto.student.StudentsPage;
import ru.itis.mappers.GroupsMapper;
import ru.itis.mappers.StudentsMapper;
import ru.itis.models.Parent;
import ru.itis.models.Student;
import ru.itis.repositories.ParentsRepository;
import ru.itis.repositories.StudentsRepository;
import ru.itis.services.students.StudentsService;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class StudentParentServiceImpl implements StudentsParentsService{
    StudentsService studentsService;
    ParentsService parentsService;
    StudentsRepository studentsRepository;
    GroupsMapper groupsMapper;

    @Override
    public StudentsPage addChild(Long parentId, Long studentId) {
        Parent parent = parentsService.findById(parentId);
        Student student = studentsService.findById(studentId);

        parent.getStudents().add(student);
        student.setParent(parent);
        studentsRepository.save(student);

        return StudentsPage.builder()
                .students(groupsMapper.toStudentDtoSet(parent.getStudents()))
                .totalCount(parent.getStudents().size())
                .build();
    }

    @Override
    public StudentsPage getChildren(Long parentId) {
        Parent parent = parentsService.findById(parentId);

        return StudentsPage.builder()
                .students(groupsMapper.toStudentDtoSet(parent.getStudents()))
                .totalCount(parent.getStudents().size())
                .build();
    }
}
