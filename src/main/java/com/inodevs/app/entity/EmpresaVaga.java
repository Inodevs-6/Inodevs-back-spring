package com.inodevs.app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresa_vaga")
public class EmpresaVaga {
    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Empresa empresa;

    @Column(name = "vaga_conhecimentos_editado")
    private String conhecimentos_editado;

    @Column(name = "vaga_habilidades_editado")
    private String habilidades_editado;

    @Column(name = "vaga_atitudes_editado")
    private String atitudes_editado;

    @OneToMany(mappedBy = "empresa_vaga")
    private List<EmpresaVaga> empresa_vaga;

    public EmpresaVaga() {}

    public EmpresaVaga(String conhecimentos_editado, String habilidades_editado, String atitudes_editado) {
        this.conhecimentos_editado = conhecimentos_editado;
        this.habilidades_editado = habilidades_editado;
        this.atitudes_editado  = atitudes_editado;
    }

    public String getConhecimentosEditado() {
        return this.conhecimentos_editado;
    }

    public void setConhecimentosEditado(String conhecimentos_editado) {
        this.conhecimentos_editado = conhecimentos_editado;
    }

    public String getHabilidadesEditado() {
        return this.habilidades_editado;
    }

    public void setHabilidadesEditado(String habilidades_editado) {
        this.habilidades_editado = habilidades_editado;
    }

    public String getAtitudesEditado() {
        return this.atitudes_editado;
    }

    public void setAtitudes(String atitudes_editado) {
        this.atitudes_editado = atitudes_editado;
    }

    public List<EmpresaVaga> getEmpresaVaga() {
        return this.empresa_vaga;
    }

    public void setCandidatos(List<EmpresaVaga> empresa_vaga) {
        this.empresa_vaga = empresa_vaga;
    }

}
