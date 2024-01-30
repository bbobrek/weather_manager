package pl.bbobrek.weather.manager.domain.weather.result;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Named("randomUuIdMapper")
public class RandomUUIDMapper {

    @Named("randomUuId")
    public UUID randomUuId(Object object) {
        return UUID.randomUUID();
    }

}
