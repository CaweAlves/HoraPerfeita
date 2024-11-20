package com.cawe.horaperfeita.application.services;

import com.cawe.horaperfeita.infrastructure.external.client.openMeteor.OpenMeteor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final OpenMeteor openMeteor;

    public WeatherService() {
        this.openMeteor = new OpenMeteor();
    }

    public String getSevenDaysForecast(String latitude, String longitude) {
        this.openMeteor.addTemperature();
        return this.openMeteor.getWeatherForecastForSevenDays(latitude, longitude);
    }
}
