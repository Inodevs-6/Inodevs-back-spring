package com.inodevs.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inodevs.app.entity.DescricaoCargo;
import com.inodevs.app.repository.DescricaoCargoRepository;

@Service
public class DescricaoCargoService {
    
    @Autowired
    private DescricaoCargoRepository descricaoCargoRepository;

    public DescricaoCargo novoDescricaoCargo (DescricaoCargo descricaoCargo) {
        
        if(descricaoCargo == null ||
                descricaoCargo.getVaga() == null ||
                descricaoCargo.getVaga().isBlank() ||
                descricaoCargo.getNivel() == null ||
                descricaoCargo.getNivel().isBlank()) {
            throw new IllegalArgumentException("Preencha os campos obrigatórios!");
        }
        return descricaoCargoRepository.save(descricaoCargo);
        
    }

}