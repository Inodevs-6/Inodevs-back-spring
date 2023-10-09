package com.inodevs.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inodevs.app.entity.Vaga;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
    public Optional<Vaga> findByNomeContaining(String nome);
}