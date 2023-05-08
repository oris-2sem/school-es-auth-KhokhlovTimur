package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Parent;

public interface ParentsRepository extends JpaRepository<Parent, Long> {
}
