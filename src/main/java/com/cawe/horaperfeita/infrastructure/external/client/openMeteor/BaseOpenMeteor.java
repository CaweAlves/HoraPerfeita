package com.cawe.horaperfeita.infrastructure.external.client.openMeteor;

import com.cawe.horaperfeita.infrastructure.external.interfaces.IExternalClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
public abstract class BaseOpenMeteor implements IExternalClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String apiKey = System.getenv("OPEN_METEOR_API_KEY");
    private final String baseUrl = "https://api.open-meteo.com/v1/forecast";
    protected  UriComponentsBuilder urlWithParams;

    public String getWeatherForecast() {
        return restTemplate.getForObject(this.urlWithParams.toUriString(), String.class);
    }

    public BaseOpenMeteor addTemperature() {
        this.urlWithParams.queryParam("hourly", "temperature_2m");
        return this;
    }

    public BaseOpenMeteor addTemperatureAndRain() {
        this.urlWithParams.queryParam("hourly", "temperature_2m,rain");
        return this;
    }

    protected BaseOpenMeteor addLatitudeAndLongitude(String latitude, String longitude) {
        this.urlWithParams.queryParam("latitude", latitude);
        this.urlWithParams.queryParam("longitude", longitude);
        return this;
    }

}
