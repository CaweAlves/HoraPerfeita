package com.cawe.horaperfeita.infrastructure.external.interfaces;

public interface IForecast {
    IForecast addTemperature();

    IForecast addLatitudeAndLongitude(String latitude, String longitude);

    String getWeatherForecast();
}
