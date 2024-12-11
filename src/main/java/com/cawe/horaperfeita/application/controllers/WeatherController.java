package com.cawe.horaperfeita.application.controllers;

import com.cawe.horaperfeita.application.services.WeatherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forecast")
@RequiredArgsConstructor
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity getForecastForPreviousSevenDays(@Valid @RequestParam String latitude, @Valid @RequestParam String longitude) {
        return ResponseEntity.ok(weatherService.getSevenDaysForecast(latitude, longitude));
    }


    @GetMapping("/weather/only-temperature")
    public ResponseEntity getForecastTemperatureForPreviousSevenDays(@Valid @RequestParam String latitude, @Valid @RequestParam String longitude) {
        return ResponseEntity.ok(weatherService.getTemperatureSevenDaysForecast(latitude, longitude));
    }

}
