package ru.itis.services.groups;

import ru.itis.dto.group.GroupDto;
import ru.itis.dto.group.NewOrUpdateGroupDto;
import ru.itis.dto.timetable.TimetableRowsPage;
import ru.itis.models.Group;

public interface GroupsService {
    GroupDto findDtoById(Long id);

    Group findById(Long id);

    GroupDto add(NewOrUpdateGroupDto groupDto);

    GroupDto update(Long id, NewOrUpdateGroupDto groupDto);

    void delete(Long id);

}
