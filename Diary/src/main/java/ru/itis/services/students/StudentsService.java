package ru.itis.services.students;

import ru.itis.dto.student.NewOrUpdateStudentDto;
import ru.itis.dto.student.StudentDto;
import ru.itis.dto.task.TasksPage;
import ru.itis.models.Student;

public interface StudentsService {
    StudentDto findDtoById(Long id);

    StudentDto add(NewOrUpdateStudentDto studentDto);

    StudentDto update(Long id, NewOrUpdateStudentDto studentDto);

    void delete(Long id);

    Student findById(Long id);

    TasksPage getTasks(Long id, int pageNumber);
}
