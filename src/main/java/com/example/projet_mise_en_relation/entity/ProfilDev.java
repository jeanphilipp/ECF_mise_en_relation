package com.example.projet_mise_en_relation.entity;

import jakarta.persistence.*;

@Entity
public class ProfilDev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private String technoMaitrisees; // ex: "Java, Spring, Angular"

    private Integer anciennete; // en années (optionnel)
    private String description;

    // Chaque ProfilDev est rattaché à 1 Utilisateur (DEV)
    @OneToOne
    @JoinColumn(name = "dev_id", nullable = false, unique = true)
    private Utilisateur dev;

    // ----- Constructeurs -----
    public ProfilDev() { }

    public ProfilDev(String technoMaitrisees, Integer anciennete, String description, Utilisateur dev) {
        this.technoMaitrisees = technoMaitrisees;
        this.anciennete = anciennete;
        this.description = description;
        this.dev = dev;
    }

    // ----- Getters/Setters -----
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTechnoMaitrisees() { return technoMaitrisees; }
    public void setTechnoMaitrisees(String technoMaitrisees) { this.technoMaitrisees = technoMaitrisees; }

    public Integer getAnciennete() { return anciennete; }
    public void setAnciennete(Integer anciennete) { this.anciennete = anciennete; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Utilisateur getDev() { return dev; }
    public void setDev(Utilisateur dev) { this.dev = dev; }
}
