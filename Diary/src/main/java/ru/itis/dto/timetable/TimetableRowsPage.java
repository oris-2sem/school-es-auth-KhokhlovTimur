package ru.itis.dto.timetable;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.itis.dto.timetable.TimetableRowDto;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimetableRowsPage {
    List<TimetableRowDto> timetableRows;
    int pagesCount;
}
