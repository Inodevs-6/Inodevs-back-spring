package com.inodevs.app.controller;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inodevs.app.entity.Empresa;
import com.inodevs.app.security.JwtUtils;
import com.inodevs.app.security.Login;
import com.inodevs.app.security.Verificar;
import com.inodevs.app.service.EmpresaService;
import com.inodevs.app.service.SegurancaService;

@RestController
@CrossOrigin
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private SegurancaService segurancaService;

    @Autowired 
    private EmpresaService empresaService;
    
    @PostMapping
    public Login autenticar(@RequestBody Login login) throws JsonProcessingException {
        Authentication auth = new UsernamePasswordAuthenticationToken(login.getUsername(), 
            login.getPassword());
        auth = authManager.authenticate(auth);
        login.setPassword(null);
    
        Empresa empresa = empresaService.buscarEmpresaPorEmail(login.getUsername());
        
        if (empresa.getTfaAtivado() == false) {
            login.setToken(JwtUtils.generateToken(auth));
            login.setEmpresa(empresa);
        }

        return login;
    }

    @GetMapping(value="/verificar")
    public ResponseEntity<String> verificar() {
        return ResponseEntity.ok("Token válido!");
    }

    @PatchMapping(value="/tfaemail/{email}")
    public ResponseEntity<Object> sendTfaCodeInEmail(@PathVariable("email") String email) throws AddressException, MessagingException {
        segurancaService.sendTfaEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value="/tfasms/{id}/{telefone}")
    public ResponseEntity<Object> sendTfaCodeInSMS(@PathVariable("id") Long id, @PathVariable("telefone") String telefone) {
        return null;
    }

    @PostMapping(value="/tfaverificar") 
	public Login verificarCodigo(@RequestBody Verificar verificar) throws JsonProcessingException {
        Login login = new Login();
        login.setUsername(verificar.getUsername());
        login.setPassword(verificar.getPassword());
        Authentication auth = new UsernamePasswordAuthenticationToken(login.getUsername(), 
            login.getPassword());
        login.setToken(JwtUtils.generateToken(auth));
        Empresa empresa = empresaService.buscarEmpresaPorEmail(login.getUsername());
        login.setEmpresa(empresa);
        auth = authManager.authenticate(auth);
        login.setToken(JwtUtils.generateToken(auth));
        boolean isValid = segurancaService.verificarCodigo(verificar.getUsername(), verificar.getCodigo());
		if(!isValid) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Codigo invalido!");
        }
		return login;
	}
 
}
