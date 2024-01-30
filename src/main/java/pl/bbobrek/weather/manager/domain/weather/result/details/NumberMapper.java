package pl.bbobrek.weather.manager.domain.weather.result.details;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Named("numberMapper")
public class NumberMapper {

    @Named("round")
    public Integer round(Double value) {
        return (int) Math.round(value);
    }

    @Named("roundAndMultiply")
    public Integer roundAndMultiply(Double value) {
        return round(value * 10);
    }



}
