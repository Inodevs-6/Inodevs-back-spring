package com.inodevs.app.repository;

import java.util.List;
// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inodevs.app.entity.Vaga;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
    public List<Vaga> findByNomeContaining(String nome);
}