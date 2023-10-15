package com.inodevs.app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidato")
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cand_id")
    private Long id;
    
    @Column(name = "cand_exp")
    private String experiencia;

    @Column(name = "cand_link")
    private String link;

    @OneToMany(mappedBy = "candidato")
    @JsonIgnore
    private List<CandidatoVaga> vagas;

    public Candidato() {
    }

    public Candidato(String experiencia, String link) {
        this.experiencia = experiencia;
        this.link = link;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExperiencia() {
        return this.experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<CandidatoVaga> getVagas() {
        return this.vagas;
    }

    public void setVagas(List<CandidatoVaga> vagas) {
        this.vagas = vagas;
    }

}
