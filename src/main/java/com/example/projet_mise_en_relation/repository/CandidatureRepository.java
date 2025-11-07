package com.example.projet_mise_en_relation.repository;

import com.example.projet_mise_en_relation.entity.Candidature;
import com.example.projet_mise_en_relation.enums.StatutCandidature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatureRepository extends JpaRepository<Candidature, Integer> {
    List<Candidature> findByProjet_Id(Integer projetId);
    List<Candidature> findByDev_Id(Integer devId);
    List<Candidature> findByProjet_IdAndStatus(Integer projetId, StatutCandidature status);
    boolean existsByProjet_IdAndDev_Id(Integer projetId, Integer devId);
}
