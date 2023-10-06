package com.inodevs.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inodevs.app.entity.Candidato;
import com.inodevs.app.entity.Vaga;

public interface VagaRepository extends JpaRepository<Vaga, Long> {

    public List<Candidato> findByCandidatoVagas(Long id);

}