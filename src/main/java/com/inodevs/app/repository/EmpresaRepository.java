package com.inodevs.app.repository;

import com.inodevs.app.entity.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
