package com.tpdesi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpdesi.entity.Integrante;

public interface IntegranteRepository extends JpaRepository<Integrante, Long> {
    boolean existsByDni(Long dni);
}