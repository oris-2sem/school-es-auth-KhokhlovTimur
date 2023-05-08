package ru.itis.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity(name = "user_details")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String login;
    String password;
    @Enumerated(EnumType.STRING)
    Role role;

    public enum Role {
        TEACHER,
        STUDENT,
        PARENT
    }
}
