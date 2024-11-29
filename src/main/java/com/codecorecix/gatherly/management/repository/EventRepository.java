package com.codecorecix.gatherly.management.repository;

import com.codecorecix.gatherly.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
