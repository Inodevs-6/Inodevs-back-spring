package com.inodevs.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inodevs.app.entity.Empresa;
import com.inodevs.app.security.JwtUtils;
import com.inodevs.app.security.Login;
import com.inodevs.app.service.EmpresaService;

@RestController
@CrossOrigin
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired 
    private EmpresaService empresaService;
    
    @PostMapping
    public Login autenticar(@RequestBody Login login) throws JsonProcessingException {
        Authentication auth = new UsernamePasswordAuthenticationToken(login.getUsername(), 
            login.getPassword());
        auth = authManager.authenticate(auth);
        login.setToken(JwtUtils.generateToken(auth));
        Empresa empresa = empresaService.buscarEmpresaPorEmail(login.getUsername());
        login.setEmpresa(empresa);
        return login;
    }
    
    @GetMapping(value="/verificar")
    public ResponseEntity<String> verificar() {
        return ResponseEntity.ok("Token válido!");
    }
 
}
