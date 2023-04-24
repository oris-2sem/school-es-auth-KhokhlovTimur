package ru.itis.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "groups")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_of_admission")
    Date dateOfAdmission;

    String letter;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    Set<Student> students;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Teacher teacher;

    @OneToMany(mappedBy = "group")
    @ToString.Exclude
    Set<TimetableRow> timetableRows;
}
