package com.tpdesi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpdesi.entity.Familia;

public interface FamiliaRepository extends JpaRepository<Familia, Long> {
    List<Familia> findByActivaTrue();
}
