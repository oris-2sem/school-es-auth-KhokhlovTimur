package ru.itis.security.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.itis.models.UserDetails;
import ru.itis.security.filters.AuthenticationFilter;
import ru.itis.security.filters.AuthorizationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig {
    PasswordEncoder passwordEncoder;
    UserDetailsService userDetailsServiceImpl;
    String authPath = AuthorizationFilter.AUTHENTICATION_PATH;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthorizationFilter authorizFilter,
                                           AuthenticationFilter authFilter) throws Exception {
        http.csrf().disable();
        authFilter.setFilterProcessesUrl(authPath);
        http.addFilter(authFilter)
                .addFilterBefore(authorizFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/auth/token").permitAll()
                .requestMatchers(HttpMethod.POST, "/parents", "/teachers", "/students").permitAll()

                .requestMatchers(HttpMethod.GET, "/students/**", "/teachers/**", "/tasks/**", "/timetable/**",
                        "/parents/**", "/groups/**", "/lessons/**").authenticated()

                .requestMatchers("/students/**", "/teachers/**", "/tasks/**", "/timetable/**",
                        "/parents/**", "/groups/**", "/lessons/**").hasAuthority(UserDetails.Role.TEACHER.toString())

                .requestMatchers("/parents/**").hasAuthority(UserDetails.Role.PARENT.toString())

                .anyRequest().authenticated());

        http.logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        return http.build();
    }

    @Autowired
    public void build(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder);
    }
}
