package com.inodevs.app.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inodevs.app.entity.Vaga;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
  
    public Optional<Vaga> findById(Long id);

    public List<Vaga> findByNomeContaining(String nome);
  
}