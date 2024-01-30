package pl.bbobrek.weather.manager.domain.weather.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateProvider {

    public LocalDateTime currentLocalDateTime() {
        return LocalDateTime.now();
    }

}
