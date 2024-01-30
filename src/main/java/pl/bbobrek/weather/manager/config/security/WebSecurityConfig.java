package pl.bbobrek.weather.manager.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    @Value("${weather.authorization.api-key}")
    private String apiKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authz ->
                       authz.requestMatchers("/weather/**").permitAll()
                               .requestMatchers("/actuator/**").permitAll()
                               .anyRequest().authenticated()
                )
                .addFilterBefore(new AuthorizationFilter(apiKey), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

}
