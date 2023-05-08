package ru.itis.security.filters;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.security.utils.AuthorizationsHeaderUtil;
import ru.itis.security.utils.JWTUtil;

import java.io.IOException;

@Component
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthorizationsHeaderUtil authorizationsHeaderUtil;
    private final JWTUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final String USER_PARAMETER = "login";

    public AuthenticationFilter(AuthenticationConfiguration authenticationConfiguration,
                                AuthorizationsHeaderUtil authorizationsHeaderUtil,
                                JWTUtil jwtUtil, ObjectMapper objectMapper) throws Exception {
        super(authenticationConfiguration.getAuthenticationManager());
        this.setUsernameParameter(USER_PARAMETER);
        this.authorizationsHeaderUtil = authorizationsHeaderUtil;
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        return super.attemptAuthentication(request, response);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        response.setContentType("application/json");

        GrantedAuthority grantedAuthority = authResult.getAuthorities().stream().findFirst().orElseThrow();
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();

        String token  = jwtUtil.generateAccessToken(userDetails.getUsername(), grantedAuthority.toString());
        objectMapper.writeValue(response.getWriter(), token);
    }
}
