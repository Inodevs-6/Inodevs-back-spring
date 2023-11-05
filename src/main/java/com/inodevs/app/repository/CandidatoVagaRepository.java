package com.inodevs.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inodevs.app.entity.CandidatoVaga;

@Repository
public interface CandidatoVagaRepository extends JpaRepository<CandidatoVaga, Long>{
    
    public List<CandidatoVaga> findByVagaId(Long vaga);

}
