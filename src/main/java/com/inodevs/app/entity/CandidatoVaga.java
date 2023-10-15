package com.inodevs.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidato_vaga")
@IdClass(CandidatoVagaId.class)
public class CandidatoVaga {
    
    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "cand_id")
    private Candidato candidato;
    
    @Column(name="cand_vaga_rank")
    private Integer rank;

    @Column(name="cand_vaga_pontos_cha")
    private Integer pontosCha;

    @Column(name="cand_percent_match")
    private Float percent_match;

    public Vaga getVaga() {
        return this.vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Candidato getCandidato() {
        return this.candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Integer getRank() {
        return this.rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getPontosCha() {
        return this.pontosCha;
    }

    public void setPontosCha(Integer pontosCha) {
        this.pontosCha = pontosCha;
    }

    public Float getPercent_match() {
        return this.percent_match;
    }

    public void setPercent_match(Float percent_match) {
        this.percent_match = percent_match;
    }

}
