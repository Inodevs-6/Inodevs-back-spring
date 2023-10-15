package com.inodevs.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inodevs.app.entity.Empresa;
import com.inodevs.app.repository.EmpresaRepository;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepo;

    public Empresa novaEmpresa (Empresa empresa) {
        
        if(empresa == null ||
                empresa.getNome() == null ||
                empresa.getCnpj() == null ||
                empresa.getDescricao() == null ) {
            throw new IllegalArgumentException("Os campos obrigatórios não foram preenchidos!");
        }
        return empresaRepo.save(empresa);        
    }
    
}
