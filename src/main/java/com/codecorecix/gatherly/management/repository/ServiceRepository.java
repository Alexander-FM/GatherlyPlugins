package com.codecorecix.gatherly.management.repository;

import com.codecorecix.gatherly.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Services, Integer> {
  List<Services> findBySupplierId(Integer supplierId);

}

