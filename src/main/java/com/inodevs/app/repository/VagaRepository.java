package com.inodevs.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inodevs.app.entity.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

    public Optional<Vaga> findById(Long id);

}