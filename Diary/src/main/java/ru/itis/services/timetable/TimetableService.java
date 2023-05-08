package ru.itis.services.timetable;

import ru.itis.dto.timetable.NewOrUpdateTimetableDto;
import ru.itis.dto.timetable.TimetableRowDto;
import ru.itis.dto.timetable.TimetableRowsPage;

public interface TimetableService {
    TimetableRowsPage getTimetable(int pageNumber);

    TimetableRowDto addTimetableRow(NewOrUpdateTimetableDto timetableDto);
}
