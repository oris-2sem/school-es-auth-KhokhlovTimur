package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Lesson;

public interface LessonsRepository extends JpaRepository<Lesson, Long> {
}
