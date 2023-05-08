package ru.itis.services.students;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dto.student.NewOrUpdateStudentDto;
import ru.itis.dto.student.StudentDto;
import ru.itis.dto.task.TasksPage;
import ru.itis.exceptions.NotFoundException;
import ru.itis.mappers.StudentsMapper;
import ru.itis.mappers.TasksMapper;
import ru.itis.mappers.UserDetailsMapper;
import ru.itis.models.*;
import ru.itis.repositories.StudentsRepository;
import ru.itis.repositories.TasksRepository;
import ru.itis.repositories.UserDetailsRepository;
import ru.itis.services.groups.GroupsService;
import ru.itis.services.parents.ParentsService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentsServiceImpl implements StudentsService {
    final StudentsRepository studentsRepository;
    final StudentsMapper studentsMapper;
    final GroupsService groupsService;
    final TasksMapper tasksMapper;
    final TasksRepository tasksRepository;
    final ParentsService parentService;
    final PasswordEncoder passwordEncoder;
    final UserDetailsMapper userDetailsMapper;
    final UserDetailsRepository userDetailsRepository;
    @Value("${page-size}")
    int pageSize;

    @Override
    public Student findById(Long id) {
        return getOrThrow(id);
    }

    @Override
    public StudentDto findDtoById(Long id) {
        return studentsMapper.toDto(getOrThrow(id));
    }

    @Override
    public StudentDto add(NewOrUpdateStudentDto studentDto) {
        Student student = studentsMapper.toStudent(studentDto);
        UserDetails userDetails = userDetailsMapper.toUser(studentDto);
        userDetails.setPassword(passwordEncoder.encode(studentDto.getPassword()));
        userDetails.setRole(UserDetails.Role.STUDENT);

        userDetailsRepository.save(userDetails);

        student.setUserDetails(userDetails);

        if (studentDto.getGroupId() != null) {
            student.setGroup(groupsService.findById(studentDto.getGroupId()));
        }
        if (studentDto.getParentId() != null) {
            student.setParent(parentService.findById(studentDto.getParentId()));
        }

        StudentDto studentDto1 = studentsMapper.toDto(studentsRepository.save(student));
        studentDto1.setUserDetails(userDetailsMapper.toDto(userDetails));
        return studentDto1;
    }

    @Override
    public void delete(Long id) {
        studentsRepository.delete(getOrThrow(id));
    }

    @Override
    public StudentDto update(Long id, NewOrUpdateStudentDto studentDto) {
        Student student = getOrThrow(id);
        Group newGroup = null;
        Parent parent = null;

        if (studentDto.getGroupId() != null) {
            newGroup = groupsService.findById(studentDto.getGroupId());
        }
        if (studentDto.getParentId() != null) {
            parent = parentService.findById(studentDto.getParentId());
        }

        student.setAge(studentDto.getAge());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setGroup(newGroup);
        student.setParent(parent);

        studentsRepository.save(student);

        return studentsMapper.toDto(student);
    }

    @Override
    public TasksPage getTasks(Long id, int pageNumber) {
        getOrThrow(id);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Task> tasks = tasksRepository.findAllByStudentIdOrderById(id, pageRequest);

        return TasksPage.builder()
                .tasks(tasksMapper.toDtoList(tasks.getContent()))
                .pagesCount(tasks.getTotalPages())
                .build();
    }

    private Student getOrThrow(Long id) {
        return studentsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id <" + id + "> not found"));
    }
}
