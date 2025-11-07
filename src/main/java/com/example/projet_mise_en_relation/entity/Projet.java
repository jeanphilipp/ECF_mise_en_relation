package com.example.projet_mise_en_relation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String titre;

    @Lob
    private String description;

    private LocalDate dateLivraison; // optionnelle

    @PositiveOrZero
    private Double budget;

    private String thematique;

    // Chaque projet a 1 porteur
    @ManyToOne(optional = false)
    @JoinColumn(name = "porteur_id", nullable = false)
    private Utilisateur porteur;

    // 0..* candidatures reçues
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Candidature> candidatures = new ArrayList<>();

    // ----- Constructeurs -----
    public Projet() { }

    public Projet(String titre, String description, LocalDate dateLivraison,
                  Double budget, String thematique, Utilisateur porteur) {
        this.titre = titre;
        this.description = description;
        this.dateLivraison = dateLivraison;
        this.budget = budget;
        this.thematique = thematique;
        this.porteur = porteur;
    }

    // ----- Getters/Setters -----
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDateLivraison() { return dateLivraison; }
    public void setDateLivraison(LocalDate dateLivraison) { this.dateLivraison = dateLivraison; }

    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }

    public String getThematique() { return thematique; }
    public void setThematique(String thematique) { this.thematique = thematique; }

    public Utilisateur getPorteur() { return porteur; }
    public void setPorteur(Utilisateur porteur) { this.porteur = porteur; }

    public List<Candidature> getCandidatures() { return candidatures; }
    public void setCandidatures(List<Candidature> candidatures) { this.candidatures = candidatures; }
}
