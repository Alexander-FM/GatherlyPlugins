package com.codecorecix.gatherly.management.repository;

import com.codecorecix.gatherly.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Services, Integer> {
}

