package com.example.projet_mise_en_relation.entity;

import com.example.projet_mise_en_relation.enums.StatutCandidature;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dateCandidature = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutCandidature status = StatutCandidature.EN_ATTENTE;

    @Lob
    private String message; // optionnel

    // 1 projet obligatoire
    @ManyToOne(optional = false)
    @JoinColumn(name = "projet_id", nullable = false)
    private Projet projet;

    // 1 utilisateur (DEV) obligatoire
    @ManyToOne(optional = false)
    @JoinColumn(name = "dev_id", nullable = false)
    private Utilisateur dev;

    // ----- Constructeurs -----
    public Candidature() { }

    public Candidature(Projet projet, Utilisateur dev, String message) {
        this.projet = projet;
        this.dev = dev;
        this.message = message;
    }

    // ----- Getters/Setters -----
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getDateCandidature() { return dateCandidature; }
    public void setDateCandidature(LocalDate dateCandidature) { this.dateCandidature = dateCandidature; }

    public StatutCandidature getStatus() { return status; }
    public void setStatus(StatutCandidature status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Projet getProjet() { return projet; }
    public void setProjet(Projet projet) { this.projet = projet; }

    public Utilisateur getDev() { return dev; }
    public void setDev(Utilisateur dev) { this.dev = dev; }
}
