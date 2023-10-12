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
@Table(name = "empresa_vaga")
@IdClass(EmpresaVagaId.class)
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
    
    @Column(name="vaga_conhecimentos_editada")
    private String conhecimentos_editado;

    @Column(name="vaga_habilidades_editada")
    private String habilidades_editado;

    @Column(name="vaga_atitudes_editada")
    private String atitudes_editado;

    public Vaga getVaga() {
        return this.vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
//
    public String getConhecimentosEditado() {
        return this.conhecimentos_editado;
    }

    public void setConhecimentosEditado(String conhecimentos_editado) {
        this.conhecimentos_editado = conhecimentos_editado;
    }

    public String getAtitudesEditado() {
        return this.atitudes_editado;
    }

    public void setAtitudesEditado(String atitudes_editado) {
        this.atitudes_editado = atitudes_editado;
    }

    public String getHabilidadesEditado() {
        return this.atitudes_editado;
    }

    public void setHabilidadesEditado(String habilidades_editado) {
        this.habilidades_editado = habilidades_editado;
    }
}
