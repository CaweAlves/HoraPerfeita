package com.cawe.horaperfeita.infrastructure.external.client.openMeteor;

import com.cawe.horaperfeita.infrastructure.external.data.WeatherData;
import com.cawe.horaperfeita.infrastructure.external.interfaces.IForecast;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
public class OpenMeteor implements IForecast {

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUrl = "https://api.open-meteo.com/v1/forecast";
    protected UriComponentsBuilder urlWithParams;

    @Autowired
    public OpenMeteor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.urlWithParams = UriComponentsBuilder.fromHttpUrl(baseUrl);
    }

    public WeatherData getWeatherForecast() {
        return this.toWeatherData(restTemplate.getForObject(this.urlWithParams.toUriString(), String.class));
    }

    public OpenMeteor addTemperature() {
        this.urlWithParams.queryParam("hourly", "temperature_2m");
        return this;
    }

    public OpenMeteor addTemperatureAndRain() {
        this.urlWithParams.queryParam("hourly", "temperature_2m,rain");
        return this;
    }

    public OpenMeteor addLatitudeAndLongitude(String latitude, String longitude) {
        this.urlWithParams.queryParam("latitude", latitude);
        this.urlWithParams.queryParam("longitude", longitude);
        return this;
    }

    private WeatherData toWeatherData(String weatherForecast) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            WeatherData weatherData = objectMapper.readValue(weatherForecast, WeatherData.class);
            return weatherData;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
