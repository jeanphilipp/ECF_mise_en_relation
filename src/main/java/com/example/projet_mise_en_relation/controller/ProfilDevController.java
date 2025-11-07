package com.example.projet_mise_en_relation.controller;

import com.example.projet_mise_en_relation.dto.ProfilDevDTO;
import com.example.projet_mise_en_relation.entity.ProfilDev;
import com.example.projet_mise_en_relation.entity.Utilisateur;
import com.example.projet_mise_en_relation.enums.Role;
import com.example.projet_mise_en_relation.repository.ProfilDevRepository;
import com.example.projet_mise_en_relation.repository.UtilisateurRepository;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/profils")
public class ProfilDevController {

    private final ProfilDevRepository profilRepo;
    private final UtilisateurRepository userRepo;

    public ProfilDevController(ProfilDevRepository profilRepo, UtilisateurRepository userRepo) {
        this.profilRepo = profilRepo;
        this.userRepo = userRepo;
    }

    // Créer ou mettre à jour le profil d'un DEV
    @PostMapping
    public ProfilDevDTO upsert(@RequestBody ProfilDevDTO dto) {
        Utilisateur dev = userRepo.findById(dto.getDevId())
                .orElseThrow(() -> new NoSuchElementException("Utilisateur introuvable"));
        if (dev.getRole() != Role.DEV) throw new IllegalArgumentException("Seul un DEV peut avoir un profil");

        ProfilDev profil = profilRepo.findByDev_Id(dev.getId()).orElse(new ProfilDev());
        profil.setDev(dev);
        profil.setTechnoMaitrisees(dto.getTechnoMaitrisees());
        profil.setAnciennete(dto.getAnciennete());
        profil.setDescription(dto.getDescription());

        ProfilDev saved = profilRepo.save(profil);
        return new ProfilDevDTO(saved.getId(), dev.getId(), saved.getTechnoMaitrisees(),
                saved.getAnciennete(), saved.getDescription());
    }

    // Récupérer le profil d'un dev
    @GetMapping("/dev/{devId}")
    public ProfilDevDTO getByDev(@PathVariable Integer devId) {
        ProfilDev p = profilRepo.findByDev_Id(devId)
                .orElseThrow(() -> new NoSuchElementException("Profil introuvable"));
        return new ProfilDevDTO(p.getId(), p.getDev().getId(), p.getTechnoMaitrisees(), p.getAnciennete(), p.getDescription());
    }
}
