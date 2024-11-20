package com.cawe.horaperfeita.infrastructure.external.client.openMeteor;

public class OpenMeteor extends BaseOpenMeteor {

    public String getWeatherForecastForSevenDays(String latitude, String longitude) {
        this.addLatitudeAndLongitude(latitude, longitude);
        return this.getWeatherForecast();
    }
}
