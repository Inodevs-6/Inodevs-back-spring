package com.inodevs.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inodevs.app.entity.Empresa;
import com.inodevs.app.repository.EmpresaRepository;

@Service
public class EmpresaService{

    @Autowired
    private EmpresaRepository empresaRepo;

    public Empresa novaEmpresa (Empresa empresa) {
        
        System.out.println(empresa.getNome());
        System.out.println(empresa.getCnpj());
        System.out.println(empresa.getEmail());
        System.out.println(empresa.getSenha());
        System.out.println(empresa.getSegmento());
        System.out.println(empresa.getPorte());

        if(empresa == null ||
                empresa.getNome() == null ||
                empresa.getCnpj() == null ||
                empresa.getEmail() == null ||
                empresa.getSenha() == null ||
                empresa.getSegmento() == null ||
                empresa.getPorte() == null) {
            throw new IllegalArgumentException("Os campos obrigatórios não foram preenchidos!");
        }
        return empresaRepo.save(empresa);        
    }

    public Empresa editarEmpresa(Long emp_id, Empresa empresa) {

        Optional<Empresa> empresaOp = empresaRepo.findById(emp_id);
        if(empresaOp.isEmpty()){
            throw new IllegalArgumentException("Empresa não encontrada");
        }

        Empresa newEmpresa = empresaOp.get();

        newEmpresa.setDescricao(empresa.getDescricao());
        newEmpresa.setEmail(empresa.getEmail());
        newEmpresa.setNome(empresa.getNome());
        newEmpresa.setSegmento(empresa.getSegmento());
        newEmpresa.setPorte(empresa.getPorte());

        return empresaRepo.save(newEmpresa);  
    }

    public Empresa editarSenha(Long emp_id, Empresa empresa) {

        Optional<Empresa> empresaOp = empresaRepo.findById(emp_id);
        if(empresaOp.isEmpty()){
            throw new IllegalArgumentException("Empresa não encontrada");
        }

        Empresa newEmpresa = empresaOp.get();

        newEmpresa.setSenha(empresa.getSenha());

        return empresaRepo.save(newEmpresa);  
    }

    public Empresa buscarEmpresa(Long emp_id) {
        Optional<Empresa> empresaOp = empresaRepo.findById(emp_id);
        if(empresaOp.isEmpty()) {
            throw new IllegalArgumentException("empresa não encontrada!");
        }
        return empresaOp.get();
    }
    
}