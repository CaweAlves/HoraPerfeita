package com.cawe.horaperfeita.application.controllers;


import com.cawe.horaperfeita.application.services.ActivityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {

    @Autowired
    private final ActivityService activityService;

    @GetMapping("/activity/week")
    public ResponseEntity<String> getWeekHours(@Valid @RequestParam String activity, @Valid @RequestParam String latitude, @Valid @RequestParam String longitude) {
        try {
            this.activityService.weekHours(activity, latitude, longitude);
            return ResponseEntity.ok("a");
        } catch (ResponseStatusException exception) {
            return new ResponseEntity(exception.getMessage(), exception.getStatusCode());
        }
    }

}
