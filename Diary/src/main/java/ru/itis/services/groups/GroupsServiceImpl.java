package ru.itis.services.groups;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.dto.group.GroupDto;
import ru.itis.dto.group.NewOrUpdateGroupDto;
import ru.itis.dto.timetable.TimetableRowsPage;
import ru.itis.exceptions.NotFoundException;
import ru.itis.mappers.GroupsMapper;
import ru.itis.models.Group;
import ru.itis.models.Teacher;
import ru.itis.models.TimetableRow;
import ru.itis.repositories.GroupsRepository;
import ru.itis.repositories.TimetableRowsRepository;
import ru.itis.services.teachers.TeachersService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupsServiceImpl implements GroupsService {
    final GroupsRepository groupsRepository;
    final GroupsMapper groupsMapper;
    final TeachersService teachersService;
    final TimetableRowsRepository timetableRowsRepository;
    @Value("${page-size}")
    int pageSize;

    @Override
    public GroupDto add(NewOrUpdateGroupDto groupDto) {
        Group group = groupsMapper.toGroup(groupDto);

        if (groupDto.getTeacherId() != null) {
            group.setTeacher(teachersService.findById(groupDto.getTeacherId()));
        }

        return groupsMapper.toDto(groupsRepository.save(group));
    }

    @Override
    public GroupDto findDtoById(Long id) {
        return groupsMapper.toDto(getOrThrow(id));
    }

    @Override
    public Group findById(Long id) {
        return getOrThrow(id);
    }

    @Override
    public GroupDto update(Long id, NewOrUpdateGroupDto groupDto) {
        Group group = getOrThrow(id);
        Teacher teacher = null;

        if (groupDto.getTeacherId() != null){
            teacher = teachersService.findById(groupDto.getTeacherId());
        }

        group.setTeacher(teacher);
        group.setLetter(groupDto.getLetter());

        return groupsMapper.toDto(groupsRepository.save(group));
    }

    @Override
    public void delete(Long id) {
        groupsRepository.delete(getOrThrow(id));
    }

    private Group getOrThrow(Long id) {
        return groupsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Group with id <" + id + "> not found"));
    }
}
