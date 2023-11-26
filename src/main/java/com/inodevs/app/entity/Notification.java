package com.inodevs.app.entity;
import java.time.LocalDateTime;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Empresa empresa;
    
    @Column(name = "notification_type")
    private String type;

    @Column(name = "vaga_nome")
    private String nome;

    @Column(name = "vaga_nivel")
    private String nivel;

    @Column(name = "notification_datetime")
    private LocalDateTime datetime;

    public Notification() {
    }

    public Notification(String type, String nome, String nivel, LocalDateTime datetime, Empresa empresa) {
        this.type = type;
        this.nome = nome;
        this.nivel = nivel;
        this.datetime = datetime;
        this.empresa = empresa;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
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

     public LocalDateTime getDatetime() {
        return this.datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
