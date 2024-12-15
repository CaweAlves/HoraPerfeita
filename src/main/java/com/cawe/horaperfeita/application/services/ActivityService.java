package com.cawe.horaperfeita.application.services;

import com.cawe.horaperfeita.domain.entities.Activity;
import com.cawe.horaperfeita.domain.repositories.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ActivityService {

    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    WeatherService weatherService;

    public Activity getActivity(String activity) {
        return this.activityRepository.findByName(activity);
    }

    public Map<String, BigDecimal> weekHours(String activityName, String latitude, String longitude) {
        Activity activity = this.getActivity(activityName);

        Map<String, BigDecimal> days = this.weatherService.getTemperatureSevenDaysForecast(latitude, longitude);

        Map<String, BigDecimal> perfectTimes = new LinkedHashMap<>();

        for (var day : days.entrySet()) {
            if ((day.getValue().compareTo(activity.getMinimumTemperature()) == 1 || day.getValue().compareTo(activity.getMinimumTemperature()) == 0)
                    && day.getValue().compareTo(activity.getMaximumTemperature()) == -1 || day.getValue().compareTo(activity.getMaximumTemperature()) == 0) {
                perfectTimes.put(day.getKey(), day.getValue());
            }
        }

        return perfectTimes;

    }

}
