package com.inodevs.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inodevs.app.entity.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long>{
    
}
