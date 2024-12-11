package com.cawe.horaperfeita.application.services;

import com.cawe.horaperfeita.application.dtos.activity.ActivityResponseDTO;
import com.cawe.horaperfeita.infrastructure.external.data.WeatherData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    @Autowired
    WeatherService weatherService;

    public void weekHours(String activity, String latitude, String longitude) {
        WeatherData weatherData = this.weatherService.getSevenDaysForecast(latitude, longitude);
        weatherData.getHourly().getTime().stream().map((hourly) -> hourly.startsWith("") );
    }

}
