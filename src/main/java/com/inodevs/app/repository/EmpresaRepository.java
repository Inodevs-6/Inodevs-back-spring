package com.inodevs.app.repository;

import com.inodevs.app.entity.Empresa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    public Optional<Empresa> findById(Long id);

    public Optional<Empresa> findByEmail(String email);

    public Optional<Empresa> findByEmailAndTfaTempoExpiracaoGreaterThanEqual(String email, Long tfaTempoExpiracao);

    public Optional<Empresa> findByIdAndTfaTempoExpiracaoGreaterThanEqual(Long id, Long tfaTempoExpiracao);
}
