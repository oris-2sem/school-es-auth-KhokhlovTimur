package ru.itis.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Task;

public interface TasksRepository extends JpaRepository<Task, Long> {
    Page<Task> findAllByStudentIdOrderById(Long studentId, Pageable pageable);

    Page<Task> findAllByLessonIdOrderById(Long lessonId, Pageable pageable);
}
