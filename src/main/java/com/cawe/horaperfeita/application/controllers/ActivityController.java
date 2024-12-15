package com.cawe.horaperfeita.application.controllers;


import com.cawe.horaperfeita.application.services.ActivityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {

    @Autowired
    private final ActivityService activityService;

    @GetMapping("/{activity}/week")
    public ResponseEntity<Map<String, BigDecimal>> getWeekHours(@Valid @PathVariable String activity, @Valid @RequestParam String latitude, @Valid @RequestParam String longitude) {
        try {
            Map<String, BigDecimal> perfectTimesInWeek =  this.activityService.weekHours(activity, latitude, longitude);
            return ResponseEntity.ok(perfectTimesInWeek);
        } catch (ResponseStatusException exception) {
            return new ResponseEntity(exception.getMessage(), exception.getStatusCode());
        }
    }

}
