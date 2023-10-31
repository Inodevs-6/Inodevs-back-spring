package com.inodevs.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inodevs.app.entity.EmpresaVaga;

@Repository
public interface EmpresaVagaRepository extends JpaRepository<EmpresaVaga, Long>{
    public Optional<EmpresaVaga> findByEmpresaIdAndVagaId(Long emp_id, Long vaga_id);
}
