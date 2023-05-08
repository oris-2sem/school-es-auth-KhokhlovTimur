package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Group;

public interface GroupsRepository extends JpaRepository<Group, Long> {
}
