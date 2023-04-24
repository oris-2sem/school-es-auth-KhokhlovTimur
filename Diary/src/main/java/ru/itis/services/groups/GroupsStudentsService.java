package ru.itis.services.groups;

import ru.itis.dto.student.StudentsPage;

public interface GroupsStudentsService {
    StudentsPage addStudent(Long groupId, Long studentId);

    StudentsPage getStudentsFromGroup(Long groupId);
}
