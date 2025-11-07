package com.example.projet_mise_en_relation.dto;

import com.example.projet_mise_en_relation.enums.StatutCandidature;

import java.time.LocalDate;

public class CandidatureDTO {
    private Integer id;
    private Integer projetId;
    private Integer devId;
    private String message;
    private StatutCandidature status;
    private LocalDate dateCandidature;

    public CandidatureDTO() { }

    public CandidatureDTO(Integer id, Integer projetId, Integer devId, String message,
                          StatutCandidature status, LocalDate dateCandidature) {
        this.id = id;
        this.projetId = projetId;
        this.devId = devId;
        this.message = message;
        this.status = status;
        this.dateCandidature = dateCandidature;
    }

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getProjetId() { return projetId; }
    public void setProjetId(Integer projetId) { this.projetId = projetId; }
    public Integer getDevId() { return devId; }
    public void setDevId(Integer devId) { this.devId = devId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public StatutCandidature getStatus() { return status; }
    public void setStatus(StatutCandidature status) { this.status = status; }
    public LocalDate getDateCandidature() { return dateCandidature; }
    public void setDateCandidature(LocalDate dateCandidature) { this.dateCandidature = dateCandidature; }
}
