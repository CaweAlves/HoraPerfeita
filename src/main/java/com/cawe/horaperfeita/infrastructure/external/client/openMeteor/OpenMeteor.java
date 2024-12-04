package com.cawe.horaperfeita.infrastructure.external.client.openMeteor;

import com.cawe.horaperfeita.infrastructure.external.interfaces.IForecast;
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

    public String getWeatherForecast() {
        return restTemplate.getForObject(this.urlWithParams.toUriString(), String.class);
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

}
