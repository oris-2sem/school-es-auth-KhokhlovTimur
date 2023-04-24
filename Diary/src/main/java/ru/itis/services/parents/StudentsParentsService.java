package ru.itis.services.parents;

import ru.itis.dto.student.StudentsPage;

public interface StudentsParentsService {
    StudentsPage addChild(Long parentId, Long studentId);

    StudentsPage getChildren(Long parentId);
}
