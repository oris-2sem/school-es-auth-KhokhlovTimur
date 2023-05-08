package ru.itis.services.timetable;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.dto.timetable.NewOrUpdateTimetableDto;
import ru.itis.dto.timetable.TimetableRowDto;
import ru.itis.dto.timetable.TimetableRowsPage;
import ru.itis.mappers.TimetableRowsMapper;
import ru.itis.models.TimetableRow;
import ru.itis.repositories.TimetableRowsRepository;
import ru.itis.services.groups.GroupsService;
import ru.itis.services.lessons.LessonsService;
import ru.itis.services.teachers.TeachersService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimetableServiceImpl implements TimetableService {
    final TimetableRowsRepository timetableRowsRepository;
    final TimetableRowsMapper timetableRowsMapper;
    final GroupsService groupsService;
    final TeachersService teachersService;
    final LessonsService lessonsService;
    @Value("${page-size}")
    int pageSize;

    @Override
    public TimetableRowDto addTimetableRow(NewOrUpdateTimetableDto timetableDto) {
        TimetableRow timetableRow = timetableRowsMapper.toTimetableRow(timetableDto);

        if (timetableDto.getGroupId() != null) {
            timetableRow.setGroup(groupsService.findById(timetableDto.getGroupId()));
        }
        if (timetableDto.getLessonId() != null) {
            timetableRow.setLesson(lessonsService.findById(timetableDto.getLessonId()));
        }
        if (timetableDto.getTeacherId() != null) {
            timetableRow.setTeacher(teachersService.findById(timetableDto.getTeacherId()));
        }

        return timetableRowsMapper.toDto(timetableRowsRepository.save(timetableRow));
    }

    @Override
    public TimetableRowsPage getTimetable(int pageCount) {
        PageRequest pageRequest = PageRequest.of(pageCount, pageSize);
        Page<TimetableRow> page = timetableRowsRepository.findAll(pageRequest);

        return TimetableRowsPage.builder()
                .timetableRows(timetableRowsMapper.toTimetableRowsDtoList(page.getContent()))
                .pagesCount(page.getTotalPages())
                .build();
    }
}
