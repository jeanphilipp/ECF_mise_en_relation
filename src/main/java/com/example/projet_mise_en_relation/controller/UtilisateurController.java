package com.example.projet_mise_en_relation.controller;

import com.example.projet_mise_en_relation.entity.Utilisateur;
import com.example.projet_mise_en_relation.enums.Role;
import com.example.projet_mise_en_relation.repository.UtilisateurRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurRepository repo;

    public UtilisateurController(UtilisateurRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Utilisateur create(@Valid @RequestBody Utilisateur u) {
        // sécurité minimale : s'assurer que le role est défini correctement
        if (u.getRole() == null) throw new IllegalArgumentException("role requis: PORTEUR ou DEV");
        return repo.save(u);
    }

    @GetMapping
    public List<Utilisateur> list() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Utilisateur get(@PathVariable Integer id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Utilisateur introuvable"));
    }
}
