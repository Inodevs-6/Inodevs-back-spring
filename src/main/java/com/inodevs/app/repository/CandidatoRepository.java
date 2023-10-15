package com.inodevs.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inodevs.app.entity.Candidato;

public interface CandidatoRepository extends JpaRepository<Candidato, Long>{
    
}
