package ru.itis.services.parents;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dto.parent.NewParentDto;
import ru.itis.dto.parent.ParentDto;
import ru.itis.exceptions.NotFoundException;
import ru.itis.mappers.ParentsMapper;
import ru.itis.mappers.UserDetailsMapper;
import ru.itis.models.Parent;
import ru.itis.models.UserDetails;
import ru.itis.repositories.ParentsRepository;
import ru.itis.repositories.UserDetailsRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ParentsServiceImpl implements ParentsService{
    ParentsRepository parentsRepository;
    ParentsMapper parentsMapper;
    PasswordEncoder passwordEncoder;
    UserDetailsRepository userDetailsRepository;
    UserDetailsMapper userDetailsMapper;

    @Override
    public Parent findById(Long id) {
        return getOrThrow(id);
    }

    @Override
    public ParentDto findDtoById(Long id) {
        return parentsMapper.toDto(getOrThrow(id));
    }

    @Override
    public ParentDto add(NewParentDto parentDto) {
        UserDetails userDetails = userDetailsMapper.toUser(parentDto);
        userDetails.setRole(UserDetails.Role.PARENT);
        userDetails.setPassword(passwordEncoder.encode(parentDto.getPassword()));
        userDetailsRepository.save(userDetails);

        Parent parent = parentsMapper.toParent(parentDto);
        parent.setUserDetails(userDetails);

        ParentDto parentDto1 = parentsMapper.toDto(parentsRepository.save(parent));
        parentDto1.setUserDetailsDto(userDetailsMapper.toDto(userDetails));
        return parentDto1;
    }

    private Parent getOrThrow(Long id) {
        return parentsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parent with id <" + id + "> not found"));
    }
}
