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
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @Column(name = "emp_nome")
    private String nome;

    @Column(name = "emp_cnpj")
    private String cnpj;

    @Column(name = "emp_descricao")
    private String descricao;

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private List<EmpresaVaga> vagas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<EmpresaVaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<EmpresaVaga> vagas) {
        this.vagas = vagas;
    }

    public Empresa(String nome, String cnpj, String descricao, List<EmpresaVaga> vagas) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.descricao = descricao;
        this.vagas = vagas;
    }
    
    public Empresa(){
        
    }
}
