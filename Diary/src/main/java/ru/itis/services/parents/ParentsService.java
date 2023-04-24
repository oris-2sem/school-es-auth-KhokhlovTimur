package ru.itis.services.parents;

import ru.itis.dto.parent.NewParentDto;
import ru.itis.dto.parent.ParentDto;
import ru.itis.models.Parent;

public interface ParentsService {
    Parent findById(Long id);

    ParentDto findDtoById(Long id);

    ParentDto add(NewParentDto parentDto);
}
