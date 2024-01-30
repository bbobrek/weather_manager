package pl.bbobrek.weather.manager.config.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter extends OncePerRequestFilter {

    private final String apiKey;

    public AuthorizationFilter(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerValue = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isNotEmpty(headerValue) && StringUtils.equals(apiKey, headerValue)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
