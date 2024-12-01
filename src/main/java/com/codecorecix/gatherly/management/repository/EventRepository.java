package com.codecorecix.gatherly.management.repository;

import com.codecorecix.gatherly.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
  @Query("SELECT e FROM Event e LEFT JOIN FETCH e.detalleServices WHERE e.id = :id")
  Optional<Event> findByIdWithDetalleServices(@Param("id") Integer id);
}

