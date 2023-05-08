package ru.itis.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.group.GroupDto;
import ru.itis.dto.timetable.NewOrUpdateTimetableDto;
import ru.itis.dto.timetable.TimetableRowDto;
import ru.itis.models.Group;
import ru.itis.models.TimetableRow;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TimetableRowsMapper {
    TimetableRowDto toDto(TimetableRow timetableRow);

    TimetableRow toTimetableRow(NewOrUpdateTimetableDto timetableRowDto);

    @Mapping(target = "teacher", ignore = true)
    GroupDto toGroupDto(Group group);

    List<TimetableRowDto> toTimetableRowsDtoList(List<TimetableRow> timetableRows);
}
