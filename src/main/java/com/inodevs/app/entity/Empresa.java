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

    @Column(name = "emp_email")
    private String email;

    @Column(name = "emp_descricao")
    private String descricao;

    @Column(name = "emp_senha")
    private String senha;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<EmpresaVaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<EmpresaVaga> vagas) {
        this.vagas = vagas;
    }

        public Empresa(Long id, String nome, String cnpj, String email, String descricao, String senha) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.descricao = descricao;
        this.senha = senha;
    }

    public Empresa(){
        
    }
}
