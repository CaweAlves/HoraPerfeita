package com.cawe.horaperfeita.infrastructure.external.interfaces;

import com.cawe.horaperfeita.infrastructure.external.data.WeatherData;

public interface IForecast {
    IForecast addTemperature();

    IForecast addLatitudeAndLongitude(String latitude, String longitude);

    WeatherData getWeatherForecast();
}
