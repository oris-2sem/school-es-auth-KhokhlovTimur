package ru.itis.security.details;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.exceptions.NotFoundException;
import ru.itis.models.UserDetails;
import ru.itis.repositories.UserDetailsRepository;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDetailsServiceImpl implements UserDetailsService {
    UserDetailsRepository userDetailsRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userDetailsRepository.findByLogin(username)
                .orElseThrow(() -> new NotFoundException("User with login <" + username + "> not found"));

        return new UserDetailsImpl(userDetails);
    }
}
