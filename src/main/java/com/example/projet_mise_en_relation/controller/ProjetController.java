package com.example.projet_mise_en_relation.controller;

import com.example.projet_mise_en_relation.dto.ProjetDTO;
import com.example.projet_mise_en_relation.entity.Projet;
import com.example.projet_mise_en_relation.entity.Utilisateur;
import com.example.projet_mise_en_relation.repository.ProjetRepository;
import com.example.projet_mise_en_relation.repository.UtilisateurRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projets")
public class ProjetController {

    private final ProjetRepository projetRepo;
    private final UtilisateurRepository userRepo;

    public ProjetController(ProjetRepository projetRepo, UtilisateurRepository userRepo) {
        this.projetRepo = projetRepo;
        this.userRepo = userRepo;
    }

    // CREATE
    @PostMapping
    public ProjetDTO create(@Valid @RequestBody ProjetDTO dto) {
        Utilisateur porteur = userRepo.findById(dto.getPorteurId())
                .orElseThrow(() -> new NoSuchElementException("Porteur introuvable"));

        Projet p = new Projet();
        p.setTitre(dto.getTitre());
        p.setDescription(dto.getDescription());
        p.setDateLivraison(dto.getDateLivraison());
        p.setBudget(dto.getBudget());
        p.setThematique(dto.getThematique());
        p.setPorteur(porteur);

        Projet saved = projetRepo.save(p);
        return toDTO(saved);
    }

    // READ all
    @GetMapping
    public List<ProjetDTO> list() {
        return projetRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Filters
    @GetMapping("/search")
    public List<ProjetDTO> search(
            @RequestParam(required = false) String thematique,
            @RequestParam(required = false) Double budgetMax,
            @RequestParam(required = false) String avantDate // format ISO yyyy-MM-dd
    ) {
        // simple exemple cumulatif (tu peux faire plus propre via Specification/JPA Criteria)
        List<Projet> found = projetRepo.findAll();

        if (thematique != null && !thematique.isBlank()) {
            found = found.stream()
                    .filter(p -> p.getThematique() != null &&
                            p.getThematique().toLowerCase().contains(thematique.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (budgetMax != null) {
            found = found.stream().filter(p -> p.getBudget() != null && p.getBudget() <= budgetMax)
                    .collect(Collectors.toList());
        }
        if (avantDate != null) {
            LocalDate d = LocalDate.parse(avantDate);
            found = found.stream().filter(p -> p.getDateLivraison() != null && p.getDateLivraison().isBefore(d))
                    .collect(Collectors.toList());
        }
        return found.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private ProjetDTO toDTO(Projet p) {
        return new ProjetDTO(
                p.getId(),
                p.getTitre(),
                p.getDescription(),
                p.getDateLivraison(),
                p.getBudget(),
                p.getThematique(),
                p.getPorteur() != null ? p.getPorteur().getId() : null
        );
    }
}
