package com.cawe.horaperfeita.application.services;

import com.cawe.horaperfeita.infrastructure.external.client.openMeteor.OpenMeteor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final OpenMeteor openMeteor;

    @Autowired
    public WeatherService(OpenMeteor openMeteor) {
        this.openMeteor = openMeteor;
    }

    public String getSevenDaysForecast(String latitude, String longitude) {
        OpenMeteor forecast = new OpenMeteor();
        forecast.addTemperature();
        return forecast.getWeatherForecastForSevenDays(latitude, longitude);
    }
}
