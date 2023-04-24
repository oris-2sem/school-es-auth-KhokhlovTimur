package ru.itis.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "timetable_rows")
@FieldDefaults(level = AccessLevel.PRIVATE)

//Строка в расписании (11А, Рус. яз., каб. 121-2, 12:00, Иванова A.A.)
public class TimetableRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Temporal(TemporalType.TIME)
    @Column(name = "start_time")
    LocalTime startTime;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "class_id")
    Group group;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    Teacher teacher;

    String room;
}
