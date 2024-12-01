package com.codecorecix.gatherly.management.repository;

import com.codecorecix.gatherly.entities.DetalleService;
import com.codecorecix.gatherly.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleServiceRepository extends JpaRepository<DetalleService, Integer> {
  List<DetalleService> findByEvent(Event event);

}
