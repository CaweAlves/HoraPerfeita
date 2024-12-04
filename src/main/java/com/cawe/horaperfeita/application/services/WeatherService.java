package com.cawe.horaperfeita.application.services;

import com.cawe.horaperfeita.infrastructure.external.interfaces.IForecast;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    @Autowired
    private final IForecast iForecast;

    public String getSevenDaysForecast(String latitude, String longitude) {
        iForecast.addTemperature();
        iForecast.addLatitudeAndLongitude(latitude, longitude);
        return this.iForecast.getWeatherForecast();
    }
}
