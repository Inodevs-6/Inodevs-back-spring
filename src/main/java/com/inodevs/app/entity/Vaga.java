package com.inodevs.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vaga")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaga_id")
    private Long id;

    @Column(name = "vaga_nome")
    private String nome;

    @Column(name = "vaga_nivel")
    private String nivel;
    
    @Column(name = "vaga_conhecimentos")
    private String conhecimentos;

    @Column(name = "vaga_habilidades")
    private String habilidades;

    @Column(name = "vaga_atitudes")
    private String atitudes;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
