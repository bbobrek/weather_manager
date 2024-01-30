package pl.bbobrek.weather.manager.models.weather.config;

import lombok.Builder;
import java.util.Map;

@Builder
public record ConnectionConfigRecord(String url, String apiKey, Map<String, String> params) {}
