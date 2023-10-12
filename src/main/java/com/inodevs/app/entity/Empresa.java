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
    private String emp_nome;

    @Column(name = "emp_cnpj")
    private String emp_cnpj;

    @Column(name = "emp_descricao")
    private String emp_descricao;

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private List<EmpresaVaga> vagas;

    public Empresa() {
    }

    public Empresa(String emp_nome, String emp_cnpj, String emp_descricao) {
        this.emp_nome = emp_nome;
        this.emp_cnpj = emp_cnpj;
        this.emp_descricao = emp_descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmp_nome() {
        return emp_nome;
    }

    public void setEmp_nome(String emp_nome) {
        this.emp_nome = emp_nome;
    }

    public String getEmp_cnpj() {
        return emp_cnpj;
    }

    public void setEmp_cnpj(String emp_cnpj) {
        this.emp_cnpj = emp_cnpj;
    }

    public String getEmp_descricao() {
        return emp_descricao;
    }

    public void setEmp_descricao(String emp_descricao) {
        this.emp_descricao = emp_descricao;
    }

    public List<EmpresaVaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<EmpresaVaga> vagas) {
        this.vagas = vagas;
    }

}
