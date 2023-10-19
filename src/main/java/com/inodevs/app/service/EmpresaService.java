package com.inodevs.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inodevs.app.entity.Empresa;
import com.inodevs.app.repository.EmpresaRepository;

@Service
public class EmpresaService implements IEmpresaService{

    @Autowired
    private EmpresaRepository empresaRepo;

    public Empresa buscarEmpresa(Long id) {
        Optional<Empresa> empresaOp = empresaRepo.findById(id);
        if(empresaOp.isPresent()) {
            return empresaOp.get();
        }
        throw new IllegalArgumentException("Id inválido!");
    }

    public Empresa novaEmpresa (Empresa empresa) {
        
        if(empresa == null ||
                empresa.getNome() == null ||
                empresa.getCnpj() == null ||
                empresa.getEmail() == null ||
                empresa.getSenha() == null) {
            throw new IllegalArgumentException("Os campos obrigatórios não foram preenchidos!");
        }
        return empresaRepo.save(empresa);        
    }
    
}