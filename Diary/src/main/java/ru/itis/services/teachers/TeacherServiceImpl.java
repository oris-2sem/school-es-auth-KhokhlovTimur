package ru.itis.services.teachers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dto.teacher.NewOrUpdateTeacherDto;
import ru.itis.dto.teacher.TeacherDto;
import ru.itis.dto.timetable.TimetableRowsPage;
import ru.itis.exceptions.NotFoundException;
import ru.itis.mappers.TeachersMapper;
import ru.itis.mappers.UserDetailsMapper;
import ru.itis.models.Teacher;
import ru.itis.models.UserDetails;
import ru.itis.repositories.TeachersRepository;
import ru.itis.repositories.UserDetailsRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherServiceImpl implements TeachersService {
    final TeachersRepository teachersRepository;
    final TeachersMapper teachersMapper;
    final UserDetailsRepository userDetailsRepository;
    final UserDetailsMapper userDetailsMapper;
    final PasswordEncoder passwordEncoder;

    @Override
    public void delete(Long id) {
        teachersRepository.delete(getOrThrow(id));
    }

    @Override
    public TimetableRowsPage getRows(Long id) {
        return null;
    }

    @Override
    public TeacherDto update(Long id, NewOrUpdateTeacherDto teacherDto) {
        Teacher teacher = getOrThrow(id);

        teacher.setAge(teacherDto.getAge());
        teacher.setExperience(teacherDto.getExperience());
        teacher.setFirstName(teacher.getFirstName());
        teacher.setLastName(teacher.getFirstName());

        return teachersMapper.toDto(teachersRepository.save(teacher));
    }

    @Override
    public TeacherDto add(NewOrUpdateTeacherDto teacherDto) {
        UserDetails userDetails = userDetailsMapper.toUser(teacherDto);
        userDetails.setRole(UserDetails.Role.TEACHER);
        userDetails.setPassword(passwordEncoder.encode(teacherDto.getPassword()));
        userDetailsRepository.save(userDetails);

        TeacherDto teacher = teachersMapper.toDto(teachersRepository.save(teachersMapper.toTeacher(teacherDto)));
        teacher.setUserDetailsDto(userDetailsMapper.toDto(userDetails));
        return teacher;
    }

    @Override
    public TeacherDto findDtoById(Long id) {
        return teachersMapper.toDto(getOrThrow(id));
    }

    @Override
    public Teacher findById(Long id) {
        return getOrThrow(id);
    }


    private Teacher getOrThrow(Long id) {
        return teachersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher with id <" + id + "> not found"));
    }
}
