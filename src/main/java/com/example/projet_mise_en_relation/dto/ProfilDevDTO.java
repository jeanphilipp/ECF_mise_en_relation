package com.example.projet_mise_en_relation.dto;

public class ProfilDevDTO {
    private Integer id;
    private Integer devId;
    private String technoMaitrisees;
    private Integer anciennete;
    private String description;

    public ProfilDevDTO() { }

    public ProfilDevDTO(Integer id, Integer devId, String technoMaitrisees,
                        Integer anciennete, String description) {
        this.id = id;
        this.devId = devId;
        this.technoMaitrisees = technoMaitrisees;
        this.anciennete = anciennete;
        this.description = description;
    }

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getDevId() { return devId; }
    public void setDevId(Integer devId) { this.devId = devId; }
    public String getTechnoMaitrisees() { return technoMaitrisees; }
    public void setTechnoMaitrisees(String technoMaitrisees) { this.technoMaitrisees = technoMaitrisees; }
    public Integer getAnciennete() { return anciennete; }
    public void setAnciennete(Integer anciennete) { this.anciennete = anciennete; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
