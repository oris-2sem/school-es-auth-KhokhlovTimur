package ru.itis.security.utils;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthorizationsHeaderUtil {
    boolean hasAuthorizationToken(HttpServletRequest request);

    String getToken(HttpServletRequest request);
}
