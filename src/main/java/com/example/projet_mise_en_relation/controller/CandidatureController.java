package com.example.projet_mise_en_relation.controller;

import com.example.projet_mise_en_relation.dto.CandidatureDTO;
import com.example.projet_mise_en_relation.entity.Candidature;
import com.example.projet_mise_en_relation.entity.Projet;
import com.example.projet_mise_en_relation.entity.Utilisateur;
import com.example.projet_mise_en_relation.enums.Role;
import com.example.projet_mise_en_relation.enums.StatutCandidature;
import com.example.projet_mise_en_relation.repository.CandidatureRepository;
import com.example.projet_mise_en_relation.repository.ProjetRepository;
import com.example.projet_mise_en_relation.repository.UtilisateurRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/candidatures")
public class CandidatureController {

    private final CandidatureRepository candRepo;
    private final ProjetRepository projetRepo;
    private final UtilisateurRepository userRepo;

    public CandidatureController(CandidatureRepository candRepo, ProjetRepository projetRepo, UtilisateurRepository userRepo) {
        this.candRepo = candRepo;
        this.projetRepo = projetRepo;
        this.userRepo = userRepo;
    }

    // DEV postule à un projet
    @PostMapping
    public CandidatureDTO postuler(@Valid @RequestBody CandidatureDTO dto) {
        Projet projet = projetRepo.findById(dto.getProjetId())
                .orElseThrow(() -> new NoSuchElementException("Projet introuvable"));
        Utilisateur dev = userRepo.findById(dto.getDevId())
                .orElseThrow(() -> new NoSuchElementException("Utilisateur introuvable"));

        if (dev.getRole() != Role.DEV) {
            throw new IllegalArgumentException("Seul un utilisateur DEV peut candidater");
        }
        if (candRepo.existsByProjet_IdAndDev_Id(projet.getId(), dev.getId())) {
            throw new IllegalArgumentException("Déjà candidat sur ce projet");
        }

        Candidature c = new Candidature();
        c.setProjet(projet);
        c.setDev(dev);
        c.setMessage(dto.getMessage());
        // status EN_ATTENTE + date déjà initialisés par défaut dans l'entité
        Candidature saved = candRepo.save(c);
        return toDTO(saved);
    }

    // Lister candidatures d'un projet
    @GetMapping("/projet/{projetId}")
    public List<CandidatureDTO> listByProjet(@PathVariable Integer projetId) {
        return candRepo.findByProjet_Id(projetId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Lister candidatures d'un dev
    @GetMapping("/dev/{devId}")
    public List<CandidatureDTO> listByDev(@PathVariable Integer devId) {
        return candRepo.findByDev_Id(devId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Accepter / Refuser
    @PostMapping("/{id}/accepter")
    public CandidatureDTO accepter(@PathVariable Integer id) {
        Candidature c = candRepo.findById(id).orElseThrow();
        c.setStatus(StatutCandidature.ACCEPTEE);
        return toDTO(candRepo.save(c));
    }

    @PostMapping("/{id}/refuser")
    public CandidatureDTO refuser(@PathVariable Integer id) {
        Candidature c = candRepo.findById(id).orElseThrow();
        c.setStatus(StatutCandidature.REFUSEE);
        return toDTO(candRepo.save(c));
    }

    private CandidatureDTO toDTO(Candidature c) {
        return new CandidatureDTO(
                c.getId(),
                c.getProjet() != null ? c.getProjet().getId() : null,
                c.getDev() != null ? c.getDev().getId() : null,
                c.getMessage(),
                c.getStatus(),
                c.getDateCandidature()
        );
    }
}
