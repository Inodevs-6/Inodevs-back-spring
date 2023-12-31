package com.inodevs.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.inodevs.app.entity.Candidato;
import com.inodevs.app.repository.CandidatoRepository;

@Service
public class CadidatoService {

    @Autowired
    private CandidatoRepository candidatoRepo;
    
    @PreAuthorize("isAuthenticated")
    public List<Candidato> buscarTodos() {
        return candidatoRepo.findAll();
    }

}