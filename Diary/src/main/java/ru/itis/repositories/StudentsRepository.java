package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Student;

public interface StudentsRepository extends JpaRepository<Student, Long> {
}
