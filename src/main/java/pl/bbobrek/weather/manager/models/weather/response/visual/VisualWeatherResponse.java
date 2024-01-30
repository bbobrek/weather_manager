package pl.bbobrek.weather.manager.models.weather.response.visual;

import lombok.Data;
import java.time.Instant;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class VisualWeatherResponse {

    private String latitude;
    private String longitude;
    private VisualCurrentConditions currentConditions;
    private List<VisualDailyConditions> days;

    public List<VisualDailyConditions> getDays() {
        return days.stream()
                .sorted(Comparator.comparing(VisualDailyConditions::getDatetimeEpoch))
                .skip(1)
                .collect(Collectors.toList());
    }

    public List<VisualHourlyConditions> getHourlyConditions() {
        return days.stream()
                .map(VisualDailyConditions::getHours)
                .flatMap(Collection::stream)
                .filter(hourlyCondition -> isAfterNow(hourlyCondition.getDatetimeEpoch()))
                .sorted(Comparator.comparing(VisualHourlyConditions::getDatetimeEpoch))
                .limit(48) // we are taking hourly results for next 48h
                .collect(Collectors.toList());
    }

    private boolean isAfterNow(Instant dateTimeEpoch) {
        return dateTimeEpoch.isAfter(Instant.now());
    }

}
