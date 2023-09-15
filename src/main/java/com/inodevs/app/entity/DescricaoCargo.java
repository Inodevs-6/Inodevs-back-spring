package com.inodevs.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "desc_cargo")
public class DescricaoCargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "desc_id")
    private Long id;

    @Column(name = "desc_vaga")
    private String vaga;

    @Column(name = "desc_nivel")
    private String nivel;
    
    @Column(name = "desc_conhecimentos")
    private String conhecimentos;

    @Column(name = "desc_habilidades")
    private String habilidades;

    @Column(name = "desc_atitudes")
    private String atitudes;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVaga() {
        return this.vaga;
    }

    public void setVaga(String vaga) {
        this.vaga = vaga;
    }

    public String getNivel() {
        return this.nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getConhecimentos() {
        return this.conhecimentos;
    }

    public void setConhecimentos(String conhecimentos) {
        this.conhecimentos = conhecimentos;
    }

    public String getHabilidades() {
        return this.habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public String getAtitudes() {
        return this.atitudes;
    }

    public void setAtitudes(String atitudes) {
        this.atitudes = atitudes;
    }

}
