package ru.itis.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "lessons")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String subject;

    String theme;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    Set<TimetableRow> timetableRows;

    @OneToMany(mappedBy = "lesson")
    @ToString.Exclude
    Set<Task> tasks;
}
