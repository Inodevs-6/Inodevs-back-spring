package com.inodevs.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inodevs.app.entity.Vaga;
import com.inodevs.app.repository.VagaRepository;


@Service
public class VagaService {
    
    @Autowired
    private VagaRepository vagaRepository;

    @Transactional
    public Vaga novaVaga (Vaga vaga) {
        
        if(vaga == null ||
                vaga.getNome() == null ||
                vaga.getNome().isBlank() ||
                vaga.getNivel() == null ||
                vaga.getNivel().isBlank()) {
            throw new IllegalArgumentException("Os campos obrigatórios não foram preenchidos!");
        }
        return vagaRepository.save(vaga);
        
    }

}