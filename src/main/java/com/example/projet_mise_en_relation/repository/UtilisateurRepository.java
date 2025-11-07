package com.example.projet_mise_en_relation.repository;

import com.example.projet_mise_en_relation.entity.Utilisateur;
import com.example.projet_mise_en_relation.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    List<Utilisateur> findByRole(Role role);
    Optional<Utilisateur> findByEmail(String email);
}
