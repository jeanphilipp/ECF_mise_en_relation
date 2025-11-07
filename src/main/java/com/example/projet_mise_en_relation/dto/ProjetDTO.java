package com.example.projet_mise_en_relation.dto;

import java.time.LocalDate;

public class ProjetDTO {
    private Integer id;
    private String titre;
    private String description;
    private LocalDate dateLivraison;
    private Double budget;
    private String thematique;
    private Integer porteurId;

    public ProjetDTO() { }

    public ProjetDTO(Integer id, String titre, String description, LocalDate dateLivraison,
                     Double budget, String thematique, Integer porteurId) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.dateLivraison = dateLivraison;
        this.budget = budget;
        this.thematique = thematique;
        this.porteurId = porteurId;
    }

    // getters/setters
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
    public Integer getPorteurId() { return porteurId; }
    public void setPorteurId(Integer porteurId) { this.porteurId = porteurId; }
}
