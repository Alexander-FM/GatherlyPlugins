package com.codecorecix.gatherly.management.repository;

import com.codecorecix.gatherly.entities.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotationRepository extends JpaRepository<Quotation, Integer> {
}

