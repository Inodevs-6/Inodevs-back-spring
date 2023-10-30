package com.inodevs.app.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inodevs.app.entity.Autorizacao;
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
        return User.builder().username(username).password(empresa.getSenha())
            .authorities(empresa.getAutorizacoes().stream()
            .map(Autorizacao::getEmail).collect(Collectors.toList())
            .toArray(new String[empresa.getAutorizacoes().size()]))
        .build();
    }
    
}
