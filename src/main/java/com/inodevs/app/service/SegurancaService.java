package com.inodevs.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inodevs.app.entity.Empresa;
import com.inodevs.app.repository.EmpresaRepository;

@Service
public class SegurancaService implements UserDetailsService{
    
    @Autowired
    private EmpresaRepository empresaRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Empresa> empresaOp = empresaRepo.findByEmail(username);
        if(empresaOp.isEmpty()) {
            throw new UsernameNotFoundException("Empresa não encontrado!");
        }
        Empresa empresa = empresaOp.get();
        return User.builder().username(empresa.getEmail()).password(empresa.getSenha())
        .build();
    }
    
}
