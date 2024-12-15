package com.cawe.horaperfeita.domain.repositories;

import com.cawe.horaperfeita.domain.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Activity findByName(String activity);

}
