package ru.itis.repositories;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.TimetableRow;

public interface TimetableRowsRepository extends JpaRepository<TimetableRow, Long> {
    Page<TimetableRow> findAllByLessonIdOrderById(Pageable pageable, Long lessonId);

    Page<TimetableRow> findAllByTeacherIdOrderById(Pageable pageable, Long teacherId);

    Page<TimetableRow> findAllByGroupIdOrderById(Pageable pageable, Long groupId);

    @NonNull Page<TimetableRow> findAll(@NonNull Pageable pageable);


}
