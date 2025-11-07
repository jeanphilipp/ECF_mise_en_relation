package com.example.projet_mise_en_relation.repository;

import com.example.projet_mise_en_relation.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet, Integer> {
    List<Projet> findByPorteur_Id(Integer porteurId);
    List<Projet> findByThematiqueContainingIgnoreCase(String thematique);
    List<Projet> findByBudgetLessThanEqual(Double budgetMax);
    List<Projet> findByDateLivraisonBefore(LocalDate dateLimite);
}
