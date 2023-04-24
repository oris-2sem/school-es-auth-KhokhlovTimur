package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Teacher;

public interface TeachersRepository extends JpaRepository<Teacher, Long> {
}
