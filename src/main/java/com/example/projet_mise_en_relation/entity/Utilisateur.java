package com.example.projet_mise_en_relation.entity;

import com.example.projet_mise_en_relation.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nom;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // PORTEUR ou DEV

    // 0..* Projets publiés (si role = PORTEUR)
    @OneToMany(mappedBy = "porteur", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Projet> projetsPublies = new ArrayList<>();

    // 0..1 Profil (si role = DEV) - optionnel côté Utilisateur
    @OneToOne(mappedBy = "dev", optional = true)
    private ProfilDev profil;

    // 0..* Candidatures (si role = DEV)
    @OneToMany(mappedBy = "dev")
    private List<Candidature> candidatures = new ArrayList<>();

    // ----- Constructeurs requis JPA -----
    public Utilisateur() { }

    public Utilisateur(String nom, String email, Role role) {
        this.nom = nom;
        this.email = email;
        this.role = role;
    }

    // ----- Getters/Setters -----
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public List<Projet> getProjetsPublies() { return projetsPublies; }
    public void setProjetsPublies(List<Projet> projetsPublies) { this.projetsPublies = projetsPublies; }

    public ProfilDev getProfil() { return profil; }
    public void setProfil(ProfilDev profil) { this.profil = profil; }

    public List<Candidature> getCandidatures() { return candidatures; }
    public void setCandidatures(List<Candidature> candidatures) { this.candidatures = candidatures; }
}
