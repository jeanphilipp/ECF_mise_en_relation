package com.example.projet_mise_en_relation.repository;

import com.example.projet_mise_en_relation.entity.ProfilDev;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfilDevRepository extends JpaRepository<ProfilDev, Integer> {
    Optional<ProfilDev> findByDev_Id(Integer devId);
}
