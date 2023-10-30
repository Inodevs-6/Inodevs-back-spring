package com.inodevs.app.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    @Column(name = "emp_segmento")
    private String segmento;

    public enum Porte {
        micro,
        pequeno,
        medio,
        grande
    }
 
    @Enumerated(EnumType.STRING)
    @Column(name = "emp_porte")
    private Porte porte;

    @OneToMany(mappedBy = "empresa")
    private List<EmpresaVaga> vagas;

    @ManyToMany
    @JoinTable(name = "uau_empresa_autorizacao",
        joinColumns = { @JoinColumn(name = "emp_id")},
        inverseJoinColumns = { @JoinColumn(name = "aut_id") }
        )
    private List<Autorizacao> autorizacoes;

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

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public List<Autorizacao> getAutorizacoes() {
        return autorizacoes;
    }

    public void setAutorizacoes(List<Autorizacao> autorizacoes) {
        this.autorizacoes = autorizacoes;
    }

    public Empresa(String nome, String cnpj, String email, String descricao, String senha, String segmento, Porte porte,
        List<Autorizacao> autorizacoes) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.descricao = descricao;
        this.senha = senha;
        this.segmento = segmento;
        this.porte = porte;
        this.autorizacoes = autorizacoes;
    }

    public Empresa(){
        
    }
}
